#include <unordered_set>
#include <iostream>
#include <unistd.h>
#include <sys/stat.h>
#include <fcntl.h>
using namespace std;
int main(){
    unordered_set<string> values;
    string init = "\x99\xad\xc2\x31";
    values.insert(init);
    size_t ans_bucket = values.bucket(init);
    int fd = open('./all_hash', O_RDONLY);
    char buf[6];
    int ret;
    int counter = 0
    while(1){
        ret = read(fd, buf, 5);
        if(ret <= 0){
            break
        }
        buf[4] = '\0';
        string input = str(buf);
        size_t bucket = values.bucket(buf)
        if(bucket == ans_bucket){
            cout << counter << endl;
        }
        counter += 1
    }
    return 0;
}
