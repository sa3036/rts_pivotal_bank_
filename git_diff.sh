
declare -a revisions=( a7c76eb c6e22ea ec332e5 5f661bd 3e080e3)
file_name=revisions_diff_files.txt

# current= 
# a7c76eb

# for revision in ${revisions[@]}
for (( c=0; c<=3; c++ ))
do
  echo 'for' '->' ${revisions[c]} .. ${revisions[c+1]}
  current=${revisions[c]}
  next=${revisions[c+1]}
  filename=revisions_diff_$current\_$next.txt
  echo $filename
  git diff --name-only $current $next > $filename

done