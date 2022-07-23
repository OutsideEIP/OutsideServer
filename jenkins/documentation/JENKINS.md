<div align="center">
    <img src="./assets/jenkins.png"/>
</div>

---

## Versions

Jenkins version : [2.34.8]()

2.36 version is available but there is error with plugins.txt

## Security

There is security issues in these plugins.

https://www.jenkins.io/security/advisory/2022-06-22/

## How to use

Prerequisites:

Copy .env.example, rename it in ".env" and fill it.

```bash
$ docker-compose up --build -d
```

## Useful Information

Jenkins run on `8081:8080` port.


## Remarks

### Plugins

Mailer, Matrix project, Pipeline: Job need an higher Jenkins version



© Copyright 2022 - Outside