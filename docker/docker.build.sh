#!/bin/sh

ROOT_PATH=$(pwd)
cd $ROOT_PATH/docker
docker build -t loanservice -f Dockerfile ..
