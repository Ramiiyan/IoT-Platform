#!/bin/bash

# Check if the environment variables are set
if [[ -z "$MQTT_BROKER_URL" || -z "$MQTT_CLIENT_ID" ]]; then
    echo "Error: MQTT_BROKER_URL or MQTT_CLIENT_ID environment variables are not set."
    exit 1
fi

# Define the paths
sample_file="application.yml.sample"
output_file="application.yml"

# Check if the sample file exists
if [[ ! -f "$sample_file" ]]; then
    echo "Error: $sample_file not found."
    exit 1
fi

# Replace placeholders in the sample file with environment variables
sed -e "s|<broker-url>|$MQTT_BROKER_URL|g" -e "s|<client-id>|$MQTT_CLIENT_ID|g" "$sample_file" > "$output_file"

echo "New application.yml file created successfully."