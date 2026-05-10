https://www.microsoft.com/en-us/download/details.aspx?id=57467 
downlaod .msi and .exe file and install them on pc

Add this path in environmental variable -> System Variables -> Path -> edit and new :
C:\Program Files\Microsoft MPI\Bin

check on powershell :
mpiexec

check gcc is installed or not using:
gcc --version

if not installed :
https://www.msys2.org/ 
download from it and installed .exe file 

From Start menu open MSYS2 UCRT64 terminal::
pacman -S mingw-w64-ucrt-x86_64-gcc
then press y
now check gcc is installed or not: gcc --version

Add GCC to PATH :Add this folder:->  C:\msys64\ucrt64\bin ->to Environment Variables → Path.
now check gcc is installed or not: gcc --version


```
cd /c/Users/91911/eclipse-workspace/mpi -> run this in MSY terminal
gcc sum_array.c -o sum_array.exe -I"/c/Program Files (x86)/Microsoft SDKs/MPI/Include" -L"/c/Program Files (x86)/Microsoft SDKs/MPI/Lib/x64" -lmsmpi
"/c/Program Files/Microsoft MPI/Bin/mpiexec.exe" -n 4 ./sum_array.exe
```
