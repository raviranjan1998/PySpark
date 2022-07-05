# to generate user file containing node ids

import string
with open("helper.txt", "w") as output:
  for i in range(1, 932):
    output.write(str(i) + '\t' + str(i) + '\n')





# to sort graphx output acc to rank #
with open("op_graphx.txt", "rt") as openfile:
    new_list = []
    for line in openfile:
        new_list.append(line.rstrip("()\n"))



def my_sort(line):
    line_fields = line.strip().split(',')
    amount = float(line_fields[1])
    return amount


new_list.sort(key=my_sort, reverse = True)

with open("graphx.txt", "w") as output:
  for line in new_list:
    output.write(line + '\n')
