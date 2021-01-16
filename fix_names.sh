#!/bin/bash

cd TextTransformerClient/target/
mv  "$(ls *-jar-with-dependencies.jar)"  "$(ls *-jar-with-dependencies.jar | cut -d'-' -f 1-2).jar"