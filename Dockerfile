FROM ubuntu:latest
LABEL authors="shant"

ENTRYPOINT ["top", "-b"]