javac -cp antlr-4.9.2-complete.jar *.java
java -cp .:antlr-4.9.2-complete.jar Test input output
llvm-link output libsysy/lib.ll -S -o out.ll
lli out.ll