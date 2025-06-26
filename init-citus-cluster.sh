#!/bin/bash
set -e

# Function to wait for PostgreSQL to be ready
wait_for_postgres() {
  local host=$1
  local port=$2
  echo "Waiting for PostgreSQL at $host:$port..."
  while ! pg_isready -h $host -p $port -q; do
    sleep 1
  done
  echo "PostgreSQL at $host:$port is ready!"
}

# Wait for both coordinator and worker to be ready
wait_for_postgres coordinator 5432
wait_for_postgres worker 5432

# Add worker node to the coordinator
psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" --dbname "$POSTGRES_DB" <<-EOSQL
  SELECT * FROM master_add_node('worker', 5432);
EOSQL

echo "Worker node has been added to the Citus cluster!"
