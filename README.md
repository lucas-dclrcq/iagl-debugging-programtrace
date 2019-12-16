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
- Click on the run button

## Implemented commands

|Command|Real-time|Replay|
|---	|---	|--- |
|step|ok|    |
|step-over|   	|    |
|continue|   	|    |
|temporaries|   	|    |
|stack|   	|    |
|stack-top|   	|    |
|receiver|   	|    |
|sender|   	|    |
|receiver-variables|   	|    |
|method|  ok	|    |
|arguments|   	|    |
|print-var| ok |    |
|break|   	|    |
|breakpoints|   	|    |
|break-once|   	|    |
|break-on-count|   	|    |
|break-before-method|   	|    |
