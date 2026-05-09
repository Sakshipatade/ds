# Distributed Systems – MPI Practical

Practical 14: Distributed sum of N array elements using MPI.

The program splits an array of `N = 16` elements among `n` processes (each gets `N/n` elements). Every process computes a **partial sum** on its chunk and prints it. The root process then reduces all partial sums into the total.

---

## Prerequisite – Install OpenMPI

```bash
sudo apt update
sudo apt install -y openmpi-bin libopenmpi-dev
```

Verify:
```bash
mpicc --version
mpirun --version
```

---

## How to Run

### Step 1 – Compile
```bash
cd mpi
mpicc sum_array.c -o sum_array
```

### Step 2 – Run with `n` processes
```bash
mpirun -np 4 ./sum_array
```

`-np 4` means 4 processes. `N (16)` must be divisible by `n`, so valid values are 1, 2, 4, 8, 16.

### Try different values of n:
```bash
mpirun -np 2 ./sum_array
mpirun -np 4 ./sum_array
mpirun -np 8 ./sum_array
```

> If you get **"There are not enough slots available"**, add `--oversubscribe`:
> ```bash
> mpirun -np 4 --oversubscribe ./sum_array
> ```

---

## Sample Output (with `-np 4`)
```
Array of 16 elements: 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16
Distributing 4 elements to each of 4 processes

Process 0 received elements: 1 2 3 4 => intermediate sum = 10
Process 1 received elements: 5 6 7 8 => intermediate sum = 26
Process 2 received elements: 9 10 11 12 => intermediate sum = 42
Process 3 received elements: 13 14 15 16 => intermediate sum = 58

Total sum of all 16 elements = 136
```

The order in which processes print their intermediate sums may vary on each run – that is normal because the processes execute concurrently.

---

## How it works

| MPI call | Purpose |
|----------|---------|
| `MPI_Init` | Start MPI environment |
| `MPI_Comm_rank` | Get this process's rank (0 to n-1) |
| `MPI_Comm_size` | Get total number of processes |
| `MPI_Scatter` | Root splits the array into chunks and sends one chunk to every process |
| `MPI_Reduce` | Combine all local sums into a final total at the root using `MPI_SUM` |
| `MPI_Finalize` | End MPI environment |

---

## Notes

- Default `N = 16`. To change, edit `#define N 16` at the top of `sum_array.c` and recompile.
- The code prints the array, the chunk each rank receives, the intermediate sum, and the total.
- Stop by `Ctrl + C` if needed (program exits on its own normally).
