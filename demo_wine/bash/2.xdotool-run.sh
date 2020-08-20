sleep 3

page=11101

#while [[ $page -lt 13526 ]]; do
while read -r page; do

url="https://www.wine-searcher.com/biz/producers/usa?s=$page"
echo $url

#captcha
ls ../tmp/Access*denied* && sleep 30 && xdotool mousemove 200 450 click 1; rm -rf ../tmp/Access*denied* 
#&& exit

#next page
sleep 1; xdotool mousemove 500 100 click 1; 
sleep 1; xdotool type "$url"; 
sleep 1; xdotool key Return; 
sleep 2

sleep 1; xdotool mousemove 500 425 click 3
sleep 1; xdotool mousemove 525 548 click 1

#sleep 2; xdotool type "page_$page"
sleep 1; xdotool key Return
sleep 1; xdotool key Escape
sleep 1; xdotool key Escape



#scroll down
#sleep 2; xdotool mousemove 1915 991 click 1


page=$(($page+25))

done < pages