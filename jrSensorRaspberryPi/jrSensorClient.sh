#!/bin/bash
#
# jrSensor raspberry client
#
# created by	joerg herzig / roman born
# date			02.05.2015
#
# starting		./jrSensorClient.sh &
#

URL=http://192.168.79.11:8080/jrSensorWeb/ws/sensor/

LOG="./jrSensorClient.log"
echo "starting script: `date '+%d.%m.%Y %H:%M:%S'`" >> $LOG

while true
do
	# get data from sensor (pin 7)
	VALUE=$(sudo ./lol_dht22/loldht 7 | grep "Humidity")

	# extract temperature and humidity
	HUM=( $(echo $VALUE | awk '{ print $3}'))
	TEMP=( $(echo $VALUE | awk '{ print $7}'))

	DATA='{"station":"Raspberry_1","sensor":"Sensor_R_1","value":"'$TEMP'","unit":"C"}'

	# debug
	# echo $VALUE
	# echo $TEMP
	# echo $HUM
	# echo $DATA

	# call webservice
	curl -X POST -H "Content-Type: application/json" --data $DATA $URL >> $LOG 2>&1

	sleep 30
done
