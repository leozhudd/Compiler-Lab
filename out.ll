; ModuleID = 'llvm-link'
source_filename = "llvm-link"
target datalayout = "e-m:o-p270:32:32-p271:32:32-p272:64:64-i64:64-f80:128-n8:16:32:64-S128"
target triple = "x86_64-apple-macosx10.14.0"

@array = dso_local global [110 x i32] zeroinitializer
@n = dso_local global i32 0
@.str = private unnamed_addr constant [3 x i8] c"%d\00", align 1
@.str.1 = private unnamed_addr constant [3 x i8] c"%c\00", align 1
@.str.2 = private unnamed_addr constant [4 x i8] c"%d:\00", align 1
@.str.3 = private unnamed_addr constant [4 x i8] c" %d\00", align 1
@.str.4 = private unnamed_addr constant [2 x i8] c"\0A\00", align 1

define dso_local void @init(i32 %r1) {
  %r2 = alloca i32, align 4
  store i32 %r1, i32* %r2, align 4
  %r3 = alloca i32, align 4
  store i32 1, i32* %r3, align 4
  br label %r4

r4:                                               ; preds = %r5, %0
  %r7 = load i32, i32* %r3, align 4
  %r8 = load i32, i32* %r2, align 4
  %r9 = load i32, i32* %r2, align 4
  %r10 = mul i32 %r8, %r9
  %r11 = add i32 %r10, 1
  %r12 = icmp sle i32 %r7, %r11
  %r13 = zext i1 %r12 to i32
  %r14 = icmp ne i32 %r13, 0
  br i1 %r14, label %r5, label %r6

r5:                                               ; preds = %r4
  %r15 = load i32, i32* %r3, align 4
  %r16 = getelementptr [110 x i32], [110 x i32]* @array, i32 0, i32 %r15
  %r17 = sub i32 0, 1
  store i32 %r17, i32* %r16, align 4
  %r18 = load i32, i32* %r3, align 4
  %r19 = add i32 %r18, 1
  store i32 %r19, i32* %r3, align 4
  br label %r4

r6:                                               ; preds = %r4
  ret void
}

define dso_local i32 @findfa(i32 %r20) {
  %r21 = alloca i32, align 4
  store i32 %r20, i32* %r21, align 4
  %r24 = load i32, i32* %r21, align 4
  %r25 = getelementptr [110 x i32], [110 x i32]* @array, i32 0, i32 %r24
  %r26 = load i32, i32* %r25, align 4
  %r27 = load i32, i32* %r21, align 4
  %r28 = icmp eq i32 %r26, %r27
  %r29 = zext i1 %r28 to i32
  %r30 = icmp ne i32 %r29, 0
  br i1 %r30, label %r22, label %r23

r22:                                              ; preds = %0
  %r32 = load i32, i32* %r21, align 4
  ret i32 %r32

1:                                                ; No predecessors!
  br label %r31

r23:                                              ; preds = %0
  %r33 = load i32, i32* %r21, align 4
  %r34 = getelementptr [110 x i32], [110 x i32]* @array, i32 0, i32 %r33
  %r36 = load i32, i32* %r21, align 4
  %r37 = getelementptr [110 x i32], [110 x i32]* @array, i32 0, i32 %r36
  %r38 = load i32, i32* %r37, align 4
  %r35 = call i32 @findfa(i32 %r38)
  store i32 %r35, i32* %r34, align 4
  %r39 = load i32, i32* %r21, align 4
  %r40 = getelementptr [110 x i32], [110 x i32]* @array, i32 0, i32 %r39
  %r41 = load i32, i32* %r40, align 4
  ret i32 %r41

2:                                                ; No predecessors!
  br label %r31

r31:                                              ; preds = %2, %1
  ret i32 0
}

define dso_local void @mmerge(i32 %r42, i32 %r43) {
  %r44 = alloca i32, align 4
  store i32 %r43, i32* %r44, align 4
  %r45 = alloca i32, align 4
  store i32 %r42, i32* %r45, align 4
  %r46 = alloca i32, align 4
  %r48 = load i32, i32* %r45, align 4
  %r47 = call i32 @findfa(i32 %r48)
  store i32 %r47, i32* %r46, align 4
  %r49 = alloca i32, align 4
  %r51 = load i32, i32* %r44, align 4
  %r50 = call i32 @findfa(i32 %r51)
  store i32 %r50, i32* %r49, align 4
  %r54 = load i32, i32* %r46, align 4
  %r55 = load i32, i32* %r49, align 4
  %r56 = icmp ne i32 %r54, %r55
  %r57 = zext i1 %r56 to i32
  %r58 = icmp ne i32 %r57, 0
  br i1 %r58, label %r52, label %r53

r52:                                              ; preds = %0
  %r60 = load i32, i32* %r46, align 4
  %r61 = getelementptr [110 x i32], [110 x i32]* @array, i32 0, i32 %r60
  %r62 = load i32, i32* %r49, align 4
  store i32 %r62, i32* %r61, align 4
  br label %r59

r53:                                              ; preds = %0
  br label %r59

r59:                                              ; preds = %r53, %r52
  ret void
}

define dso_local i32 @main() {
  %r63 = alloca i32, align 4
  %r64 = alloca i32, align 4
  %r65 = alloca i32, align 4
  %r66 = alloca i32, align 4
  store i32 1, i32* %r63, align 4
  br label %r67

r67:                                              ; preds = %r249, %0
  %r70 = load i32, i32* %r63, align 4
  %r71 = icmp ne i32 %r70, 0
  br i1 %r71, label %r68, label %r69

r68:                                              ; preds = %r67
  %r72 = load i32, i32* %r63, align 4
  %r73 = sub i32 %r72, 1
  store i32 %r73, i32* %r63, align 4
  store i32 4, i32* @n, align 4
  store i32 10, i32* %r64, align 4
  %r74 = alloca i32, align 4
  store i32 0, i32* %r74, align 4
  %r75 = alloca i32, align 4
  store i32 0, i32* %r75, align 4
  %r76 = load i32, i32* @n, align 4
  call void @init(i32 %r76)
  %r77 = alloca i32, align 4
  %r78 = load i32, i32* @n, align 4
  %r79 = load i32, i32* @n, align 4
  %r80 = mul i32 %r78, %r79
  %r81 = add i32 %r80, 1
  store i32 %r81, i32* %r77, align 4
  br label %r82

r82:                                              ; preds = %r98, %r68
  %r85 = load i32, i32* %r74, align 4
  %r86 = load i32, i32* %r64, align 4
  %r87 = icmp slt i32 %r85, %r86
  %r88 = zext i1 %r87 to i32
  %r89 = icmp ne i32 %r88, 0
  br i1 %r89, label %r83, label %r84

r83:                                              ; preds = %r82
  %r90 = call i32 @getint()
  store i32 %r90, i32* %r65, align 4
  %r91 = call i32 @getint()
  store i32 %r91, i32* %r66, align 4
  %r94 = load i32, i32* %r75, align 4
  %r95 = icmp eq i32 %r94, 0
  %r96 = zext i1 %r95 to i32
  %r97 = icmp ne i32 %r96, 0
  br i1 %r97, label %r92, label %r93

r92:                                              ; preds = %r83
  %r99 = alloca i32, align 4
  %r100 = load i32, i32* @n, align 4
  %r101 = load i32, i32* %r65, align 4
  %r102 = sub i32 %r101, 1
  %r103 = mul i32 %r100, %r102
  %r104 = load i32, i32* %r66, align 4
  %r105 = add i32 %r103, %r104
  store i32 %r105, i32* %r99, align 4
  %r106 = load i32, i32* %r99, align 4
  %r107 = getelementptr [110 x i32], [110 x i32]* @array, i32 0, i32 %r106
  %r108 = load i32, i32* %r99, align 4
  store i32 %r108, i32* %r107, align 4
  %r111 = load i32, i32* %r65, align 4
  %r112 = icmp eq i32 %r111, 1
  %r113 = zext i1 %r112 to i32
  %r114 = icmp ne i32 %r113, 0
  br i1 %r114, label %r109, label %r110

r109:                                             ; preds = %r92
  %r116 = getelementptr [110 x i32], [110 x i32]* @array, i32 0, i32 0
  store i32 0, i32* %r116, align 4
  %r117 = load i32, i32* %r99, align 4
  call void @mmerge(i32 %r117, i32 0)
  br label %r115

r110:                                             ; preds = %r92
  br label %r115

r115:                                             ; preds = %r110, %r109
  %r120 = load i32, i32* %r65, align 4
  %r121 = load i32, i32* @n, align 4
  %r122 = icmp eq i32 %r120, %r121
  %r123 = zext i1 %r122 to i32
  %r124 = icmp ne i32 %r123, 0
  br i1 %r124, label %r118, label %r119

r118:                                             ; preds = %r115
  %r126 = load i32, i32* %r77, align 4
  %r127 = getelementptr [110 x i32], [110 x i32]* @array, i32 0, i32 %r126
  %r128 = load i32, i32* %r77, align 4
  store i32 %r128, i32* %r127, align 4
  %r129 = load i32, i32* %r99, align 4
  %r130 = load i32, i32* %r77, align 4
  call void @mmerge(i32 %r129, i32 %r130)
  br label %r125

r119:                                             ; preds = %r115
  br label %r125

r125:                                             ; preds = %r119, %r118
  %r134 = load i32, i32* %r66, align 4
  %r135 = load i32, i32* @n, align 4
  %r136 = icmp slt i32 %r134, %r135
  %r137 = zext i1 %r136 to i32
  %r138 = icmp ne i32 %r137, 0
  br i1 %r138, label %r133, label %r132

r133:                                             ; preds = %r125
  %r139 = load i32, i32* %r99, align 4
  %r140 = add i32 %r139, 1
  %r141 = getelementptr [110 x i32], [110 x i32]* @array, i32 0, i32 %r140
  %r142 = load i32, i32* %r141, align 4
  %r143 = sub i32 0, 1
  %r144 = icmp ne i32 %r142, %r143
  %r145 = zext i1 %r144 to i32
  %r146 = icmp ne i32 %r145, 0
  br i1 %r146, label %r131, label %r132

r131:                                             ; preds = %r133
  %r148 = load i32, i32* %r99, align 4
  %r149 = load i32, i32* %r99, align 4
  %r150 = add i32 %r149, 1
  call void @mmerge(i32 %r148, i32 %r150)
  br label %r147

r132:                                             ; preds = %r133, %r125
  br label %r147

r147:                                             ; preds = %r132, %r131
  %r154 = load i32, i32* %r66, align 4
  %r155 = icmp sgt i32 %r154, 1
  %r156 = zext i1 %r155 to i32
  %r157 = icmp ne i32 %r156, 0
  br i1 %r157, label %r153, label %r152

r153:                                             ; preds = %r147
  %r158 = load i32, i32* %r99, align 4
  %r159 = sub i32 %r158, 1
  %r160 = getelementptr [110 x i32], [110 x i32]* @array, i32 0, i32 %r159
  %r161 = load i32, i32* %r160, align 4
  %r162 = sub i32 0, 1
  %r163 = icmp ne i32 %r161, %r162
  %r164 = zext i1 %r163 to i32
  %r165 = icmp ne i32 %r164, 0
  br i1 %r165, label %r151, label %r152

r151:                                             ; preds = %r153
  %r167 = load i32, i32* %r99, align 4
  %r168 = load i32, i32* %r99, align 4
  %r169 = sub i32 %r168, 1
  call void @mmerge(i32 %r167, i32 %r169)
  br label %r166

r152:                                             ; preds = %r153, %r147
  br label %r166

r166:                                             ; preds = %r152, %r151
  %r173 = load i32, i32* %r65, align 4
  %r174 = load i32, i32* @n, align 4
  %r175 = icmp slt i32 %r173, %r174
  %r176 = zext i1 %r175 to i32
  %r177 = icmp ne i32 %r176, 0
  br i1 %r177, label %r172, label %r171

r172:                                             ; preds = %r166
  %r178 = load i32, i32* %r99, align 4
  %r179 = load i32, i32* @n, align 4
  %r180 = add i32 %r178, %r179
  %r181 = getelementptr [110 x i32], [110 x i32]* @array, i32 0, i32 %r180
  %r182 = load i32, i32* %r181, align 4
  %r183 = sub i32 0, 1
  %r184 = icmp ne i32 %r182, %r183
  %r185 = zext i1 %r184 to i32
  %r186 = icmp ne i32 %r185, 0
  br i1 %r186, label %r170, label %r171

r170:                                             ; preds = %r172
  %r188 = load i32, i32* %r99, align 4
  %r189 = load i32, i32* %r99, align 4
  %r190 = load i32, i32* @n, align 4
  %r191 = add i32 %r189, %r190
  call void @mmerge(i32 %r188, i32 %r191)
  br label %r187

r171:                                             ; preds = %r172, %r166
  br label %r187

r187:                                             ; preds = %r171, %r170
  %r195 = load i32, i32* %r65, align 4
  %r196 = icmp sgt i32 %r195, 1
  %r197 = zext i1 %r196 to i32
  %r198 = icmp ne i32 %r197, 0
  br i1 %r198, label %r194, label %r193

r194:                                             ; preds = %r187
  %r199 = load i32, i32* %r99, align 4
  %r200 = load i32, i32* @n, align 4
  %r201 = sub i32 %r199, %r200
  %r202 = getelementptr [110 x i32], [110 x i32]* @array, i32 0, i32 %r201
  %r203 = load i32, i32* %r202, align 4
  %r204 = sub i32 0, 1
  %r205 = icmp ne i32 %r203, %r204
  %r206 = zext i1 %r205 to i32
  %r207 = icmp ne i32 %r206, 0
  br i1 %r207, label %r192, label %r193

r192:                                             ; preds = %r194
  %r209 = load i32, i32* %r99, align 4
  %r210 = load i32, i32* %r99, align 4
  %r211 = load i32, i32* @n, align 4
  %r212 = sub i32 %r210, %r211
  call void @mmerge(i32 %r209, i32 %r212)
  br label %r208

r193:                                             ; preds = %r194, %r187
  br label %r208

r208:                                             ; preds = %r193, %r192
  %r217 = getelementptr [110 x i32], [110 x i32]* @array, i32 0, i32 0
  %r218 = load i32, i32* %r217, align 4
  %r219 = sub i32 0, 1
  %r220 = icmp ne i32 %r218, %r219
  %r221 = zext i1 %r220 to i32
  %r222 = icmp ne i32 %r221, 0
  br i1 %r222, label %r216, label %r214

r216:                                             ; preds = %r208
  %r223 = load i32, i32* %r77, align 4
  %r224 = getelementptr [110 x i32], [110 x i32]* @array, i32 0, i32 %r223
  %r225 = load i32, i32* %r224, align 4
  %r226 = sub i32 0, 1
  %r227 = icmp ne i32 %r225, %r226
  %r228 = zext i1 %r227 to i32
  %r229 = icmp ne i32 %r228, 0
  br i1 %r229, label %r215, label %r214

r215:                                             ; preds = %r216
  %r230 = call i32 @findfa(i32 0)
  %r232 = load i32, i32* %r77, align 4
  %r231 = call i32 @findfa(i32 %r232)
  %r233 = icmp eq i32 %r230, %r231
  %r234 = zext i1 %r233 to i32
  %r235 = icmp ne i32 %r234, 0
  br i1 %r235, label %r213, label %r214

r213:                                             ; preds = %r215
  store i32 1, i32* %r75, align 4
  %r237 = alloca i32, align 4
  %r238 = load i32, i32* %r74, align 4
  %r239 = add i32 %r238, 1
  store i32 %r239, i32* %r237, align 4
  %r240 = load i32, i32* %r237, align 4
  call void @putint(i32 %r240)
  call void @putch(i32 10)
  br label %r236

r214:                                             ; preds = %r215, %r216, %r208
  br label %r236

r236:                                             ; preds = %r214, %r213
  br label %r98

r93:                                              ; preds = %r83
  br label %r98

r98:                                              ; preds = %r93, %r236
  %r241 = load i32, i32* %r74, align 4
  %r242 = add i32 %r241, 1
  store i32 %r242, i32* %r74, align 4
  br label %r82

r84:                                              ; preds = %r82
  %r245 = load i32, i32* %r75, align 4
  %r246 = icmp eq i32 %r245, 0
  %r247 = zext i1 %r246 to i32
  %r248 = icmp ne i32 %r247, 0
  br i1 %r248, label %r243, label %r244

r243:                                             ; preds = %r84
  %r250 = sub i32 0, 1
  call void @putint(i32 %r250)
  call void @putch(i32 10)
  br label %r249

r244:                                             ; preds = %r84
  br label %r249

r249:                                             ; preds = %r244, %r243
  br label %r67

r69:                                              ; preds = %r67
  ret i32 0

1:                                                ; No predecessors!
  ret i32 0
}

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
