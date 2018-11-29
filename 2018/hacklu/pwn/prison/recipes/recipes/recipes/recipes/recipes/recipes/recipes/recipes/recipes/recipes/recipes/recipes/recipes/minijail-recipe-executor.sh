#!/bin/sh

target="$1"

# nobody can escape the minijail!?
./minijail0 -n -S /app/minijail-seccomp.policy "$target"

