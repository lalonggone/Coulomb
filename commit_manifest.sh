if [ "$1" = "" ]
then
    echo "please specify a message"
    exit
fi
cd io.github.hamza_algohary.Coulomb/ && git add io.github.hamza_algohary.Coulomb.yaml && git commit -m "$1"