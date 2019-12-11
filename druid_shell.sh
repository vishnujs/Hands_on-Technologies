#!/bin/sh

echo -e "1.coordinator \n 2.Overload \n 3.Historicals \n 4.MiddleManager \n 5.Brocker"
read inpChoice
case $inpChoice in
	1)
			echo "Starting coordinator"
			nohup java `cat conf/druid/coordinator/jvm.config | xargs` -cp conf/druid/_common:conf/druid/coordinator:lib/* io.druid.cli.Main server coordinator > log/coordiantor.log 2>&1 &
			;;
	2)
			echo "Starting overload"
			nohup java `cat conf/druid/overlord/jvm.config | xargs` -cp conf/druid/_common:conf/druid/overlord:lib/* io.druid.cli.Main server overlord > log/overload.log 2>&1 &
			;;
	3)		
			echo "Starting historical"
			nohup java `cat conf/druid/historical/jvm.config | xargs` -cp conf/druid/_common:conf/druid/historical:lib/* io.druid.cli.Main server historical > log/historical.log 2>&1 &
			;;
	4)		
			echo "Starting middleManager"
			nohup java `cat conf/druid/middleManager/jvm.config | xargs` -cp conf/druid/_common:conf/druid/middleManager:lib/* io.druid.cli.Main server middleManager > log/middleManager.log 2>&1 &
			;;
	5)
			echo "Starting brocker"
			nohup java `cat conf/druid/broker/jvm.config | xargs` -cp conf/druid/_common:conf/druid/broker:lib/* io.druid.cli.Main server broker > log/brocker.log 2>&1 &
			;;
	*)
			echo "Not a choice!!"
			;;
esac
