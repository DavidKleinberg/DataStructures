#David Kleinberg
#dkleinb1@jhu.edu

python makedata.py 1000 1000000 0 > 1k.txt

{ time java Words < 1k.txt > /dev/null 2>&1 ; } 2> Time.txt 

{ time java Words < 1k.txt > /dev/null 2>&1 ; } 2>> Time.txt 

{ time java Words < 1k.txt > /dev/null 2>&1 ; } 2>> Time.txt 

rm 1k.txt

echo ""
echo "Average time for random 1k:"

java AverageTimes < Time.txt

rm Time.txt

#sorted
python makedata.py 1000 1000000 0 > 1.txt

sort -k 1 1.txt > 1k.txt

{ time java Words < 1k.txt > /dev/null 2>&1 ; } 2> Time.txt 

{ time java Words < 1k.txt > /dev/null 2>&1 ; } 2>> Time.txt 

{ time java Words < 1k.txt > /dev/null 2>&1 ; } 2>> Time.txt 

rm 1.txt
rm 1k.txt

echo ""
echo "Average time for sorted 1k:"

java AverageTimes < Time.txt

rm Time.txt

# 10 thousand

python makedata.py 10000 1000000 0 > 10k.txt

{ time java Words < 10k.txt > /dev/null 2>&1 ; } 2> Time.txt 

{ time java Words < 10k.txt > /dev/null 2>&1 ; } 2>> Time.txt 

{ time java Words < 10k.txt > /dev/null 2>&1 ; } 2>> Time.txt 

rm 10k.txt

echo ""
echo "Average time for random 10k:"

java AverageTimes < Time.txt

rm Time.txt

#sorted

python makedata.py 10000 1000000 0 > 10.txt

sort -k 1 10.txt > 10k.txt

{ time java Words < 10k.txt > /dev/null 2>&1 ; } 2> Time.txt 

{ time java Words < 10k.txt > /dev/null 2>&1 ; } 2>> Time.txt 

{ time java Words < 10k.txt > /dev/null 2>&1 ; } 2>> Time.txt 

rm 10.txt
rm 10k.txt

echo ""
echo "Average time for sorted 10k:"

java AverageTimes < Time.txt

rm Time.txt

# 100 thousand

python makedata.py 100000 1000000 0 > 100k.txt

{ time java Words < 100k.txt > /dev/null 2>&1 ; } 2> Time.txt 

{ time java Words < 100k.txt > /dev/null 2>&1 ; } 2>> Time.txt 

{ time java Words < 100k.txt > /dev/null 2>&1 ; } 2>> Time.txt 

rm 100k.txt

echo ""
echo "Average time for random 100k:"

java AverageTimes < Time.txt

rm Time.txt

#sorted
python makedata.py 100000 1000000 0 > 100.txt

sort -k 1 100.txt > 100k.txt

{ time java Words < 100k.txt > /dev/null 2>&1 ; } 2> Time.txt 

{ time java Words < 100k.txt > /dev/null 2>&1 ; } 2>> Time.txt 

{ time java Words < 100k.txt > /dev/null 2>&1 ; } 2>> Time.txt 

rm 100.txt
rm 100k.txt

echo ""
echo "Average time for sorted 100k:"

java AverageTimes < Time.txt

rm Time.txt

# 1 Mil

python makedata.py 1000000 100000000 0 > 1M.txt

{ time java Words < 1M.txt > /dev/null 2>&1 ; } 2> Time.txt 

{ time java Words < 1M.txt > /dev/null 2>&1 ; } 2>> Time.txt 

{ time java Words < 1M.txt > /dev/null 2>&1 ; } 2>> Time.txt 

rm 1M.txt

echo ""
echo "Average time for random 1M:"

java AverageTimes < Time.txt

rm Time.txt

# 1 Mil

python makedata.py 1000000 100000000 0 > 1.txt

sort -k 1 1.txt > 1M.txt

{ time java Words < 1M.txt > /dev/null 2>&1 ; } 2> Time.txt 

{ time java Words < 1M.txt > /dev/null 2>&1 ; } 2>> Time.txt 

{ time java Words < 1M.txt > /dev/null 2>&1 ; } 2>> Time.txt 

rm 1.txt
rm 1M.txt

echo ""
echo "Average time for sorted 1M:"

java AverageTimes < Time.txt

rm Time.txt



