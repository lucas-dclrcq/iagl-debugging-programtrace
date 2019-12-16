# CAL/DEBUGGING - TP3

[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=program-trace&metric=alert_status)](https://sonarcloud.io/dashboard?id=program-trace) [![GitHub Actions](https://img.shields.io/endpoint.svg?url=https%3A%2F%2Factions-badge.atrox.dev%2Fatrox%2Fsync-dotenv%2Fbadge&label=build&logo=none)](https://github.com/lucas-dclrcq/iagl-debugging-programtrace)

## Prerequisites

- Java version : 11

## How to run

_via Maven_

```shell script
mvn clean compile exec:exec
```

_via Intellij_

- Create a running configuration
- Modify the working directory, it should be target/classes
- Add an integer argument that represent the line number on which to set a first breakpoint (example: 6)
- Click on the run button

Notes:
- To go into replay mode you should enter `replay on` (`replay off` to exit)

## Implemented commands

|Command|Real-time|Replay|
|---	|---	|--- |
|step|:heavy_check_mark:|   :heavy_check_mark:|
|step-over|   :heavy_check_mark:|    |
|continue|   	|    |
|temporaries|  :heavy_check_mark:	|  :heavy_check_mark: |
|stack|    :heavy_check_mark: |    |
|stack-top|   	|    |
|receiver|   	|    |
|sender|   :heavy_check_mark: |    |
|receiver-variables|   	|    |
|method|  :heavy_check_mark:	|   :heavy_check_mark:|
|arguments|   	|    |
|print-var| :heavy_check_mark: |    |
|frame| :heavy_check_mark: |    |
|break|   	|    |
|breakpoints|  :heavy_check_mark:	|    |
|break-once|   	|    |
|break-on-count|   	|    |
|break-before-method|   	|    |
