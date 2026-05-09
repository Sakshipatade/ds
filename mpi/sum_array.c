#include <stdio.h>
#include <stdlib.h>
#include <mpi.h>

#define N 16

int main(int argc, char *argv[]) {
    int rank, size;
    int arr[N];
    int local_sum = 0, total_sum = 0;

    MPI_Init(&argc, &argv);
    MPI_Comm_rank(MPI_COMM_WORLD, &rank);
    MPI_Comm_size(MPI_COMM_WORLD, &size);

    if (N % size != 0) {
        if (rank == 0) {
            printf("Error: N (%d) must be divisible by number of processes (%d)\n", N, size);
        }
        MPI_Finalize();
        return 1;
    }

    int chunk = N / size;
    int *local_arr = (int *) malloc(chunk * sizeof(int));

    if (rank == 0) {
        printf("Array of %d elements: ", N);
        for (int i = 0; i < N; i++) {
            arr[i] = i + 1;
            printf("%d ", arr[i]);
        }
        printf("\n");
        printf("Distributing %d elements to each of %d processes\n\n", chunk, size);
    }

    MPI_Scatter(arr, chunk, MPI_INT, local_arr, chunk, MPI_INT, 0, MPI_COMM_WORLD);

    printf("Process %d received elements: ", rank);
    for (int i = 0; i < chunk; i++) {
        printf("%d ", local_arr[i]);
        local_sum += local_arr[i];
    }
    printf("=> intermediate sum = %d\n", local_sum);

    MPI_Reduce(&local_sum, &total_sum, 1, MPI_INT, MPI_SUM, 0, MPI_COMM_WORLD);

    if (rank == 0) {
        printf("\nTotal sum of all %d elements = %d\n", N, total_sum);
    }

    free(local_arr);
    MPI_Finalize();
    return 0;
}
