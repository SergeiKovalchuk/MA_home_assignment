# MA_home_assignment

## How to run the home assignment application

-   pull or build the docker image into the machine
    -   docker pull sergeikov/ma-home:1.0.0 (from docker hub)
    -   OR docker build . --tag=pi-log-manager if building from Dockerfile and compiled jar

-   docker run -v <local/path/to/files/folder>:</any/path/on/container> <image-name>:<image-tag> group <path/to/file>
    -   -v is required when running on a local machine to mount folder with files to the docker container
    -   Example: docker run -v e:\Projects\MedAware_home_assignment\fileMount:/app/files/ pi-log-manager:latest group /app/files/task_input
    -   can also run the filter function by runnning the same cli command with replacing "group" argument with filter
