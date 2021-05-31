# Assignment-1-DSA
This was done for my Data Structures and Algorithms paper at AUT


<h2>LinkedSet and LinkedRRSet</h2>

![image](https://user-images.githubusercontent.com/12677108/118343064-ca52e580-b57a-11eb-8ab1-fce1e321b452.png)
![image](https://user-images.githubusercontent.com/12677108/118343083-e0f93c80-b57a-11eb-9e69-a4d072303090.png)

This program above was making a LinkedSet and LinkedRRSet. LinkedSet is an linked list implementation but with a set
so it cannot contain duplicates.

<h2>Hot Plate</h2>

![image](https://user-images.githubusercontent.com/12677108/118343190-8f04e680-b57b-11eb-9dc5-6eb8daa2d4c9.png)

Each element in the grid is a thread and its temperature is accessed by other threads as well. The other threads would be the neighbours of that element.
The threads are synchronized so that they do not run into race conditions.

Clicking on an element will apply the temperature

![hotplate gui](https://user-images.githubusercontent.com/12677108/120131956-908e0a00-c21d-11eb-89a9-ff1ecf66d0a6.png)

The temperature slider represents how much temperature to apply and the heat constant the rate of application.
