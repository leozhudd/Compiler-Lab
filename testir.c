void foo(int a[]) {
    a[0] = 1;
    return;
}
int main(){
    int p[99];
    foo(p);
}