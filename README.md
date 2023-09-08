# MA_home_assignment

##How to run the home assignment application

-   Load or build the docker image into the machine
    -   docker load -i pi-log-manager OR docker build . --tag=pi-log-manager
-   docker run -v <local/path/to/files/folder>:</any/path/on/container> <image-name>:<image-tag> <function group|filter> <path/to/file>
    -   -v is required when running on a local machine to mount folder with files to the docker container
        -   Example: docker run -v e:\Projects\MedAware_home_assignment\fileMount:/app/files/ pi-log-manager:latest group /app/files/task_input
    -   Note: all the mount and file access should be part of a docker compose and wrapped in API.
