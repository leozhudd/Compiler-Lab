; ModuleID = 'llvm-link'
source_filename = "llvm-link"
target datalayout = "e-m:o-p270:32:32-p271:32:32-p272:64:64-i64:64-f80:128-n8:16:32:64-S128"
target triple = "x86_64-apple-macosx10.14.0"

@.str = private unnamed_addr constant [3 x i8] c"%d\00", align 1
@.str.1 = private unnamed_addr constant [3 x i8] c"%c\00", align 1
@.str.2 = private unnamed_addr constant [4 x i8] c"%d:\00", align 1
@.str.3 = private unnamed_addr constant [4 x i8] c" %d\00", align 1
@.str.4 = private unnamed_addr constant [2 x i8] c"\0A\00", align 1

define dso_local i32 @exgcd(i32 %r1, i32 %r2, i32* %r3, i32* %r4) {
  %r5 = icmp eq i32 %r2, 0
  %r6 = zext i1 %r5 to i32
  %r7 = icmp ne i32 %r6, 0
  br i1 %r7, label %r8, label %r9

r8:                                               ; preds = %0
  %r11 = getelementptr i32, i32* %r3, i32 0
  store i32 1, i32* %r11, align 4
  %r12 = getelementptr i32, i32* %r4, i32 0
  store i32 0, i32* %r12, align 4
  ret i32 %r1

1:                                                ; No predecessors!
  br label %r10

r9:                                               ; preds = %0
  %r13 = alloca i32, align 4
  %r15 = srem i32 %r1, %r2
  %r16 = getelementptr i32, i32* %r3, i32 0
  %r17 = getelementptr i32, i32* %r4, i32 0
  %r14 = call i32 @exgcd(i32 %r2, i32 %r15, i32* %r16, i32* %r17)
  store i32 %r14, i32* %r13, align 4
  %r18 = alloca i32, align 4
  %r19 = getelementptr i32, i32* %r3, i32 0
  %r20 = load i32, i32* %r19, align 4
  store i32 %r20, i32* %r18, align 4
  %r21 = getelementptr i32, i32* %r3, i32 0
  %r22 = getelementptr i32, i32* %r4, i32 0
  %r23 = load i32, i32* %r22, align 4
  store i32 %r23, i32* %r21, align 4
  %r24 = getelementptr i32, i32* %r4, i32 0
  %r25 = load i32, i32* %r18, align 4
  %r26 = sdiv i32 %r1, %r2
  %r27 = getelementptr i32, i32* %r4, i32 0
  %r28 = load i32, i32* %r27, align 4
  %r29 = mul i32 %r26, %r28
  %r30 = sub i32 %r25, %r29
  store i32 %r30, i32* %r24, align 4
  %r31 = load i32, i32* %r13, align 4
  ret i32 %r31

2:                                                ; No predecessors!
  br label %r10

r10:                                              ; preds = %2, %1
  ret i32 0
}

define dso_local i32 @main() {
  %r32 = alloca i32, align 4
  store i32 7, i32* %r32, align 4
  %r33 = alloca i32, align 4
  store i32 15, i32* %r33, align 4
  %r34 = alloca [1 x i32], align 4
  %r_for_memset35 = getelementptr [1 x i32], [1 x i32]* %r34, i32 0, i32 0
  call void @memset(i32* %r_for_memset35, i32 0, i32 4)
  %r36 = getelementptr [1 x i32], [1 x i32]* %r34, i32 0, i32 0
  store i32 1, i32* %r36, align 4
  %r37 = alloca [1 x i32], align 4
  %r_for_memset38 = getelementptr [1 x i32], [1 x i32]* %r37, i32 0, i32 0
  call void @memset(i32* %r_for_memset38, i32 0, i32 4)
  %r39 = getelementptr [1 x i32], [1 x i32]* %r37, i32 0, i32 0
  store i32 1, i32* %r39, align 4
  %r41 = load i32, i32* %r32, align 4
  %r42 = load i32, i32* %r33, align 4
  %r43 = getelementptr [1 x i32], [1 x i32]* %r34, i32 0, i32 0
  %r44 = getelementptr [1 x i32], [1 x i32]* %r37, i32 0, i32 0
  %r40 = call i32 @exgcd(i32 %r41, i32 %r42, i32* %r43, i32* %r44)
  %r45 = getelementptr [1 x i32], [1 x i32]* %r34, i32 0, i32 0
  %r46 = getelementptr [1 x i32], [1 x i32]* %r34, i32 0, i32 0
  %r47 = load i32, i32* %r46, align 4
  %r48 = load i32, i32* %r33, align 4
  %r49 = srem i32 %r47, %r48
  %r50 = load i32, i32* %r33, align 4
  %r51 = add i32 %r49, %r50
  %r52 = load i32, i32* %r33, align 4
  %r53 = srem i32 %r51, %r52
  store i32 %r53, i32* %r45, align 4
  %r54 = getelementptr [1 x i32], [1 x i32]* %r34, i32 0, i32 0
  %r55 = load i32, i32* %r54, align 4
  call void @putint(i32 %r55)
  ret i32 0

1:                                                ; No predecessors!
  ret i32 0
}

declare void @memset(i32*, i32, i32)

; Function Attrs: noinline nounwind optnone ssp uwtable
define dso_local i32 @getint() #0 {
  %1 = alloca i32, align 4
  %2 = call i32 (i8*, ...) @scanf(i8* getelementptr inbounds ([3 x i8], [3 x i8]* @.str, i64 0, i64 0), i32* %1)
  %3 = load i32, i32* %1, align 4
  ret i32 %3
}

declare i32 @scanf(i8*, ...) #1

; Function Attrs: noinline nounwind optnone ssp uwtable
define dso_local i32 @getch() #0 {
  %1 = alloca i8, align 1
  %2 = call i32 (i8*, ...) @scanf(i8* getelementptr inbounds ([3 x i8], [3 x i8]* @.str.1, i64 0, i64 0), i8* %1)
  %3 = load i8, i8* %1, align 1
  %4 = sext i8 %3 to i32
  ret i32 %4
}

; Function Attrs: noinline nounwind optnone ssp uwtable
define dso_local i32 @getarray(i32* %0) #0 {
  %2 = alloca i32*, align 8
  %3 = alloca i32, align 4
  %4 = alloca i32, align 4
  store i32* %0, i32** %2, align 8
  %5 = call i32 (i8*, ...) @scanf(i8* getelementptr inbounds ([3 x i8], [3 x i8]* @.str, i64 0, i64 0), i32* %3)
  store i32 0, i32* %4, align 4
  br label %6

6:                                                ; preds = %16, %1
  %7 = load i32, i32* %4, align 4
  %8 = load i32, i32* %3, align 4
  %9 = icmp slt i32 %7, %8
  br i1 %9, label %10, label %19

10:                                               ; preds = %6
  %11 = load i32*, i32** %2, align 8
  %12 = load i32, i32* %4, align 4
  %13 = sext i32 %12 to i64
  %14 = getelementptr inbounds i32, i32* %11, i64 %13
  %15 = call i32 (i8*, ...) @scanf(i8* getelementptr inbounds ([3 x i8], [3 x i8]* @.str, i64 0, i64 0), i32* %14)
  br label %16

16:                                               ; preds = %10
  %17 = load i32, i32* %4, align 4
  %18 = add nsw i32 %17, 1
  store i32 %18, i32* %4, align 4
  br label %6, !llvm.loop !3

19:                                               ; preds = %6
  %20 = load i32, i32* %3, align 4
  ret i32 %20
}

; Function Attrs: noinline nounwind optnone ssp uwtable
define dso_local void @putint(i32 %0) #0 {
  %2 = alloca i32, align 4
  store i32 %0, i32* %2, align 4
  %3 = load i32, i32* %2, align 4
  %4 = call i32 (i8*, ...) @printf(i8* getelementptr inbounds ([3 x i8], [3 x i8]* @.str, i64 0, i64 0), i32 %3)
  ret void
}

declare i32 @printf(i8*, ...) #1

; Function Attrs: noinline nounwind optnone ssp uwtable
define dso_local void @putch(i32 %0) #0 {
  %2 = alloca i32, align 4
  store i32 %0, i32* %2, align 4
  %3 = load i32, i32* %2, align 4
  %4 = call i32 (i8*, ...) @printf(i8* getelementptr inbounds ([3 x i8], [3 x i8]* @.str.1, i64 0, i64 0), i32 %3)
  ret void
}

; Function Attrs: noinline nounwind optnone ssp uwtable
define dso_local void @putarray(i32 %0, i32* %1) #0 {
  %3 = alloca i32, align 4
  %4 = alloca i32*, align 8
  %5 = alloca i32, align 4
  store i32 %0, i32* %3, align 4
  store i32* %1, i32** %4, align 8
  %6 = load i32, i32* %3, align 4
  %7 = call i32 (i8*, ...) @printf(i8* getelementptr inbounds ([4 x i8], [4 x i8]* @.str.2, i64 0, i64 0), i32 %6)
  store i32 0, i32* %5, align 4
  br label %8

8:                                                ; preds = %19, %2
  %9 = load i32, i32* %5, align 4
  %10 = load i32, i32* %3, align 4
  %11 = icmp slt i32 %9, %10
  br i1 %11, label %12, label %22

12:                                               ; preds = %8
  %13 = load i32*, i32** %4, align 8
  %14 = load i32, i32* %5, align 4
  %15 = sext i32 %14 to i64
  %16 = getelementptr inbounds i32, i32* %13, i64 %15
  %17 = load i32, i32* %16, align 4
  %18 = call i32 (i8*, ...) @printf(i8* getelementptr inbounds ([4 x i8], [4 x i8]* @.str.3, i64 0, i64 0), i32 %17)
  br label %19

19:                                               ; preds = %12
  %20 = load i32, i32* %5, align 4
  %21 = add nsw i32 %20, 1
  store i32 %21, i32* %5, align 4
  br label %8, !llvm.loop !5

22:                                               ; preds = %8
  %23 = call i32 (i8*, ...) @printf(i8* getelementptr inbounds ([2 x i8], [2 x i8]* @.str.4, i64 0, i64 0))
  ret void
}

attributes #0 = { noinline nounwind optnone ssp uwtable "disable-tail-calls"="false" "frame-pointer"="all" "less-precise-fpmad"="false" "min-legal-vector-width"="0" "no-infs-fp-math"="false" "no-jump-tables"="false" "no-nans-fp-math"="false" "no-signed-zeros-fp-math"="false" "no-trapping-math"="true" "stack-protector-buffer-size"="8" "target-cpu"="penryn" "target-features"="+cx16,+cx8,+fxsr,+mmx,+sahf,+sse,+sse2,+sse3,+sse4.1,+ssse3,+x87" "tune-cpu"="generic" "unsafe-fp-math"="false" "use-soft-float"="false" }
attributes #1 = { "disable-tail-calls"="false" "frame-pointer"="all" "less-precise-fpmad"="false" "no-infs-fp-math"="false" "no-nans-fp-math"="false" "no-signed-zeros-fp-math"="false" "no-trapping-math"="true" "stack-protector-buffer-size"="8" "target-cpu"="penryn" "target-features"="+cx16,+cx8,+fxsr,+mmx,+sahf,+sse,+sse2,+sse3,+sse4.1,+ssse3,+x87" "tune-cpu"="generic" "unsafe-fp-math"="false" "use-soft-float"="false" }

!llvm.ident = !{!0}
!llvm.module.flags = !{!1, !2}

!0 = !{!"Homebrew clang version 12.0.1"}
!1 = !{i32 1, !"wchar_size", i32 4}
!2 = !{i32 7, !"PIC Level", i32 2}
!3 = distinct !{!3, !4}
!4 = !{!"llvm.loop.mustprogress"}
!5 = distinct !{!5, !4}
