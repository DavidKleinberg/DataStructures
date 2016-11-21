//David Kleinberg
//dkleinb1@jhu.edu

python makedata.py 1000 1000000 90 > biased1k.txt

echo ""
echo biased1k:

./xtime java UniqueArray < biased1k.txt > /dev/null

./xtime java UniqueList < biased1k.txt > /dev/null

./xtime java UniqueTranspose < biased1k.txt > /dev/null

./xtime java UniqueMoveToFront < biased1k.txt > /dev/null

./xtime java UniqueHeapQueue < biased1k.txt > /dev/null

./xtime java UniqueSortedQueue < biased1k.txt > /dev/null

rm biased1k.txt


python makedata.py 10000 1000000 90 > biased10k.txt

echo ""
echo biased10k:

./xtime java UniqueArray < biased10k.txt > /dev/null

./xtime java UniqueList < biased10k.txt > /dev/null

./xtime java UniqueTranspose < biased10k.txt > /dev/null

./xtime java UniqueMoveToFront < biased10k.txt > /dev/null

./xtime java UniqueHeapQueue < biased10k.txt > /dev/null

./xtime java UniqueSortedQueue < biased10k.txt > /dev/null

rm biased10k.txt


python makedata.py 100000 1000000 90 > biased100k.txt

echo ""
echo biased100k:

./xtime java UniqueArray < biased100k.txt > /dev/null

./xtime java UniqueList < biased100k.txt > /dev/null

./xtime java UniqueTranspose < biased100k.txt > /dev/null

./xtime java UniqueMoveToFront < biased100k.txt > /dev/null

./xtime java UniqueHeapQueue < biased100k.txt > /dev/null

./xtime java UniqueSortedQueue < biased100k.txt > /dev/null

rm biased100k.txt


python makedata.py 1000 1000000 50 > mixed1k.txt

echo ""
echo mixed1k:

./xtime java UniqueArray < mixed1k.txt > /dev/null

./xtime java UniqueList < mixed1k.txt > /dev/null

./xtime java UniqueTranspose < mixed1k.txt > /dev/null

./xtime java UniqueMoveToFront < mixed1k.txt > /dev/null

./xtime java UniqueHeapQueue < mixed1k.txt > /dev/null

./xtime java UniqueSortedQueue < mixed1k.txt > /dev/null

rm mixed1k.txt


python makedata.py 10000 1000000 50 > mixed10k.txt

echo ""
echo mixed10k:

./xtime java UniqueArray < mixed10k.txt > /dev/null

./xtime java UniqueList < mixed10k.txt > /dev/null

./xtime java UniqueTranspose < mixed10k.txt > /dev/null

./xtime java UniqueMoveToFront < mixed10k.txt > /dev/null

./xtime java UniqueHeapQueue < mixed10k.txt > /dev/null

./xtime java UniqueSortedQueue < mixed10k.txt > /dev/null

rm mixed10k.txt


python makedata.py 100000 1000000 50 > mixed100k.txt

echo ""
echo mixed100k:

./xtime java UniqueArray < mixed100k.txt > /dev/null

./xtime java UniqueList < mixed100k.txt > /dev/null

./xtime java UniqueTranspose < mixed100k.txt > /dev/null

./xtime java UniqueMoveToFront < mixed100k.txt > /dev/null

./xtime java UniqueHeapQueue < mixed100k.txt > /dev/null

./xtime java UniqueSortedQueue < mixed100k.txt > /dev/null

rm mixed100k.txt


python makedata.py 1000 1000000 0 > random1k.txt

echo ""
echo random1k:

./xtime java UniqueArray < random1k.txt > /dev/null

./xtime java UniqueList < random1k.txt > /dev/null

./xtime java UniqueTranspose < random1k.txt > /dev/null

./xtime java UniqueMoveToFront < random1k.txt > /dev/null

./xtime java UniqueHeapQueue < random1k.txt > /dev/null

./xtime java UniqueSortedQueue < random1k.txt > /dev/null

rm random1k.txt


python makedata.py 10000 1000000 0 > random10k.txt

echo ""
echo random10k:

./xtime java UniqueArray < random10k.txt > /dev/null

./xtime java UniqueList < random10k.txt > /dev/null

./xtime java UniqueTranspose < random10k.txt > /dev/null

./xtime java UniqueMoveToFront < random10k.txt > /dev/null

./xtime java UniqueHeapQueue < random10k.txt > /dev/null

./xtime java UniqueSortedQueue < random10k.txt > /dev/null

rm random10k.txt


python makedata.py 100000 1000000 0 > random100k.txt

echo ""
echo random100k:

./xtime java UniqueArray < random100k.txt > /dev/null

./xtime java UniqueList < random100k.txt > /dev/null

./xtime java UniqueTranspose < random100k.txt > /dev/null

./xtime java UniqueMoveToFront < random100k.txt > /dev/null

./xtime java UniqueHeapQueue < random100k.txt > /dev/null

./xtime java UniqueSortedQueue < random100k.txt > /dev/null

rm random100k.txt

