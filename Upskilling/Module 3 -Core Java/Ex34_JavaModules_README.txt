 Project structure:
//   modules/
//   ├── com.utils/
//   │   ├── module-info.java
//   │   └── com/utils/StringUtils.java
//   └── com.greetings/
//       ├── module-info.java
//       └── com/greetings/Main.java
//
// ── com.utils/module-info.java ──────────────────────────────────────
// module com.utils {
//     exports com.utils;
// }
//
// ── com.utils/com/utils/StringUtils.java ────────────────────────────
// package com.utils;
//
// public class StringUtils {
//     public static String toUpperCase(String s) {
//         return s.toUpperCase();
//     }
//     public static String greetingFor(String name) {
//         return "Hello, " + name + "!";
//     }
// }
//
// ── com.greetings/module-info.java ──────────────────────────────────
// module com.greetings {
//     requires com.utils;
// }
//
// ── com.greetings/com/greetings/Main.java ───────────────────────────
// package com.greetings;
//
// import com.utils.StringUtils;
//
// public class Main {
//     public static void main(String[] args) {
//         String msg = StringUtils.greetingFor("World");
//         System.out.println(StringUtils.toUpperCase(msg));
//     }
// }
//
// ── Compile and Run ──────────────────────────────────────────────────
// # Step 1: Compile com.utils
// javac -d mods/com.utils com.utils/module-info.java com.utils/com/utils/StringUtils.java
//
// # Step 2: Compile com.greetings (depends on com.utils)
// javac --module-path mods -d mods/com.greetings \
//     com.greetings/module-info.java com.greetings/com/greetings/Main.java
//
// # Step 3: Run
// java --module-path mods -m com.greetings/com.greetings.Main
//
// Expected output:
//   HELLO, WORLD!
 