	书中的代码有点问题,出在System.out.printf("%s : %d\n", Thread.currentThread().getId(), result); 这行, 
书中里面的是"%f\n",如果这么写的话,异常会出现在这里.这样的会有问题.另外书中只创建了2个线程,过少,也很难看出实际效果.
可以多创建几个.