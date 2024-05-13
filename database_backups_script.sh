#!/bin/bash

if [ -f .env ]; then
    source .env
fi

DATE=$(date +%F-%H%M%S)

# Backup directory on the host
BACKUP_DIR="/var/backups/database_backups"

# Database credentials and details
DB_HOST="${DESOFS_DB_HOST}" #name of the mysql container
DB_USER="${DESOFS_DB_USER}"
DB_PASSWORD="${DESOFS_DB_PASS}"
DB_NAME="${DESOFS_DB_DEV_DB_NAME}"
NETWORK="cozinha_na_cozinha" #name of the network where mysql container is running. You can check the list of the docker neworks using doocker network ls

# Docker image version of MySQL
MYSQL_IMAGE="mysql:latest"

# Backup filename
BACKUP_FILENAME="$BACKUP_DIR/$DB_NAME-$DATE.sql"

# Run mysqldump within a new Docker container
docker run --rm --network $NETWORK $MYSQL_IMAGE \\
  /usr/bin/mysqldump -h $DB_HOST -u $DB_USER -p$DB_PASSWORD $DB_NAME > $BACKUP_FILENAME

# Compress the backup file
gzip $BACKUP_FILENAME