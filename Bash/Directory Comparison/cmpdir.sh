#!/bin/bash

declare -a arr1 arr2

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
path1=$(locate --all -b -e -n 1 $1)
path2=$(locate --all -b -e -n 1 $2)
path3=$(locate --all -b -e -n 1 $3)
curdir=$(pwd)

if [ -d "$path1" ] && [ -d "$path2" ] && [ -d "$path3" ] ; then
	echo All directories exist.
else
	if [ ! -d "$path1" ] ; then
		echo First directory does not exist, please try again.
		exit
	fi
	if [ ! -d "$path2" ] ; then
		echo Second directory does not exist, please try again.
		exit
	fi
	if [ ! -d "$path3" ] ; then
		echo Third directory does not exist, please try again.
		exit
	fi
fi

printf "\n"

files1=$(diff -r $path1 $path2 | grep -w $fdir | wc -l )
files2=$(diff -r $path1 $path2 | grep -w $sdir | wc -l )

#Actions for first directory
for (( i=1; i<=$files1; i++))
do
	arr1+=$(diff -r $path1 $path2 | grep -w $fdir | cut -d ' ' -f4 | head -$i | tail -$(( $files1 -$i + 1 )))
	arr1+=' '
done
cd $path1
for (( i=0; i<$files1; i++ ))
do
	total1=0
	total1+=$(du -h ${arr1[$i]} | cut -f -1)
done
echo The number of files only in the first direcory are: $files1
echo The total size of the files is: $total1

printf "\n"
cd $curdir

#Actions for second directory
for (( i=1; i<=$files2; i++ ))
do
	arr2+=$(diff -r $path1 $path2 | grep -w $sdir | cut -d ' ' -f4 | head -$i | tail -$(( $files2 -$i + 1 )))
	arr2+=' '
done
cd $path2
for (( i=0; i<$files2; i++))
do
	total2=0
	total2+=$(du -h ${arr2[$i]} | cut -f -1)
done
echo The number of files only in the first direcory are: $files2
echo The total size of the files is: $total2

printf "\n"
cd $curdir

#For both directories
echo All the files that differ in both directories are: $arr1 $arr2
echo The total size of the files that differ in both directories is: $(( $total1 + $total2 ))

#Actions for third directory
cd $path1
for i in $(seq $files1)
do
	touch hard${arr1[$i]}.txt
	mv ${arr1[$i]} $path3
	ln $i.txt $path3/${arr1[$i]}
	echo Hard link for ${arr1[$i]} is $i.txt
done

printf "\n"

cd $path2
for i in $(seq $files2)
do
	touch hard${arr2[$i]}.txt
	mv ${arr2[$i]} $path3
	ln $i.txt $path3/${arr2[$i]}
	echo Hard link for ${arr2[$i]} is $i.txt
done
