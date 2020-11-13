# Spring JPA hibernate examples

## Database

We will use docker to boot up postgres docker instance.

- version : 12.5
- username : postgres
- password : docker


1. Let's create a volume directory to save docker instance data.

```bash
mkdir -p $HOME/docker/volumes/postgres 
```

2. Start the docker container

```bash
docker run --rm --name pg-docker -e POSTGRES_PASSWORD=docker -d -p 5432:5432 -v $HOME/docker/volumes/postgres:/var/lib/postgresql/data postgres:12.5
```


## Examples

### Hibernate Image example
- Store image in the database
- [Project Repo](hibernate-image-example/)
