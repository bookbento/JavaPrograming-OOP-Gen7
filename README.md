Text-Based RPG Game

รายละเอียดโปรเจกต์
โปรเจกต์นี้เป็นเกม RPG แบบข้อความ (Text-based RPG) พัฒนาโดยใช้ภาษา Java เพื่อจำลองสถานการณ์การเล่นเกมพื้นฐานผ่าน Console โดยเน้นการใช้แนวคิด Object-Oriented Programming (OOP) อย่างครบถ้วน
ผู้เล่นสามารถเลือกอาชีพ ต่อสู้กับศัตรู ใช้ไอเท็ม และจัดการสถานะตัวละครได้ พร้อมระบบบันทึกและโหลดเกม


Feature
- เลือกอาชีพตัวละคร (Warrior / Mage)
- ระบบต่อสู้ (Attack, HP, Damage, Death)
- เป้าหมาย 2 ประเภท:
   ศัตรู (Enemy)
   วัตถุ (Chest)
- ระบบไอเท็ม:
   Health Potion (ฟื้นฟู HP)
- Game Loop เล่นต่อเนื่อง
- ระบบบันทึก/โหลดเกม (player.txt)
- แสดงสถานะตัวละคร
- สุ่มศัตรู (Random Enemy)
- ระบบเลเวล (Leveling)


How to run
1. Compile
javac -d out src/rpg/*.java
2. Run
java -cp out rpg.Main


หมายเหตุ
ไฟล์เซฟจะถูกสร้างชื่อ: player.txt
อยู่ใน root ของโปรเจกต์


Programing Structure (OOP)
- คลาสหลัก
   Main → จุดเริ่มต้นโปรแกรม
   Game → ควบคุม game loop และ logic หลัก
- คลาสตัวละคร
   Character (abstract)
   Warrior (inherits Character)
   Mage (inherits Character)
- เป้าหมาย (Targets)
   Enemy
   Chest
- ไอเท็ม
   Item
   HealthPotion
- Interfaces
   Attackable → ใช้กับตัวละครและศัตรู
   Usable → ใช้กับไอเท็ม
- การจัดการไฟล์
   FileManager → save/load ข้อมูล


Use Cases
- สร้างหรือเลือกตัวละคร
- ต่อสู้กับศัตรู
- ใช้ไอเท็ม
- ดูสถานะตัวละคร
- จบเกม (ชนะ/แพ้)


Input Validation
- ตรวจสอบ input เป็นตัวเลข
- ตรวจสอบเมนูไม่เกินขอบเขต
- ป้องกันโปรแกรม crash


Manage Data
- ใช้ ArrayList เก็บข้อมูลในหน่วยความจำ
- บันทึก/โหลดข้อมูลผ่านไฟล์ player.txt


Example of Gameplay
- เริ่มเกม → เลือกอาชีพ
- เข้าสู่การต่อสู้
- เลือก:
   Attack
   Use Item
   View Status
- เล่นจน:
   ตัวละครตาย ❌
   หรือชนะศัตรูทั้งหมด ✅


OOP concepts used
- Inheritance (Warrior, Mage)
- Polymorphism (ใช้ Character reference)
- Interface (Attackable, Usable)
- Method Overriding (attack behavior)
- Method Overloading (method ที่รับ parameter ต่างกัน)


Bonus Features
- สุ่มศัตรู
- ระบบเลเวล


Deverloper
- 672110161 ศรัญภัทร แสงปัก
- 672110242 ยศัสวิน ปัญญาไหว

Video Demo
