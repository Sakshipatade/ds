#include <stdio.h>
#include <stdlib.h>
#include <mpi.h>

/*
 * Distributed sum of an array using MPI.
 *
 * Idea:
 *   1. Process 0 (the "root") creates the array.
 *   2. The array is split equally among all processes (Scatter).
 *   3. Every process adds up its own chunk.
 *   4. All partial sums are added together at the root (Reduce).
 */

#define N 16  /* total number of elements */

int main(int argc, char *argv[]) {
    int rank, size;            /* this process's id and total processes */
    int arr[N];                /* the full array (used only by root) */
    int local_sum = 0;         /* sum of this process's chunk */
    int total_sum = 0;         /* final sum (only valid at root) */

    /* Start MPI and find out who we are. */
    MPI_Init(&argc, &argv);
    MPI_Comm_rank(MPI_COMM_WORLD, &rank);
    MPI_Comm_size(MPI_COMM_WORLD, &size);

    /* The array must split evenly. */
    if (N % size != 0) {
        if (rank == 0) {
            printf("Error: N (%d) must be divisible by number of processes (%d)\n", N, size);
        }
        MPI_Finalize();
        return 1;
    }

    int chunk = N / size;
    int *local_arr = (int *) malloc(chunk * sizeof(int));

    /* Root fills the array with 1, 2, 3, ..., N and prints it. */
    if (rank == 0) {
        printf("Array of %d elements: ", N);
        for (int i = 0; i < N; i++) {
            arr[i] = i + 1;
            printf("%d ", arr[i]);
        }
        printf("\n");
        printf("Distributing %d elements to each of %d processes\n\n", chunk, size);
    }

    /* Scatter: root sends one chunk to every process. */
    MPI_Scatter(arr, chunk, MPI_INT, local_arr, chunk, MPI_INT, 0, MPI_COMM_WORLD);

    /* Each process sums its own chunk. */
    printf("Process %d received elements: ", rank);
    for (int i = 0; i < chunk; i++) {
        printf("%d ", local_arr[i]);
        local_sum += local_arr[i];
    }
    printf("=> intermediate sum = %d\n", local_sum);

    /* Reduce: combine all local sums into total_sum at the root. */
    MPI_Reduce(&local_sum, &total_sum, 1, MPI_INT, MPI_SUM, 0, MPI_COMM_WORLD);

    /* Only the root prints the final answer. */
    if (rank == 0) {
        printf("\nTotal sum of all %d elements = %d\n", N, total_sum);
    }

    free(local_arr);
    MPI_Finalize();
    return 0;
}
