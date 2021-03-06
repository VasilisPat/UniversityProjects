#!/bin/bash

if [ $# -eq 0 ] ; then
	echo No valid input option, please try again.
	exit
elif [ ! $2 ] ; then
  echo Missing second argument.
  exit
elif [ ! $3 ] ; then
	echo Missing third argument.
	exit
fi

sudo updatedb
src=$(locate --all -b -e -n 1 $2)
dest=$(locate --all -b -e -n 1 $3)

if getent passwd $1 ; then
  echo User exists.
else
  echo User does not exist, please try again.
  exit
fi

if [ -d "$src" ] && [ -d "$dest" ] ; then
  echo Backup source and destination exist.
else
  if [ ! -d "$src" ] ; then
    echo Backup source does not exist.
    exit
  fi
  if [ ! -d "$dest" ] ; then
    echo Backup destinastion does not exist.
    exit
  fi
fi

#Create backup
read -p "Give name for backup file: " bcpname
tar -cvpzf $bcpname.tar.gz $src
if [ -d $dest ] ; then
  mv $bcpname.tar.gz $dest
elif [ -f $dest]
  echo $bcpname.tar.gz >> $dest
fi
