# hostban

 A simple utility to "ban" certain hosts from being accessible from the current computer.
 
 This is achieved by adding a record for provided hosts in the local `hosts` file, alongside
 the IP `127.0.0.1`, effectively redirecting "banned" hosts to localhost, rendering them unreachable.
 
 ---
 
 ### Build:
 `./gradlew clean install`
 
 ### Run (with admin privilidges):
 `java -jar build/libs/hostban*.jar`
 
 ---
 
 The MVP version takes the list of hosts to be "banned" as a list in `Main.kt` 
 and supports only Windows.
 
 **Has** to be ran as an administrator in order to modify the `hosts` file!