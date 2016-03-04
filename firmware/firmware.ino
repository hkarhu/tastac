#define P1_UP     2
#define P1_DOWN   3
#define P1_LEFT   4
#define P1_RIGHT  5
#define P1_FIRE   8
#define P1_INA    6
#define P1_INB    7
#define P2_UP     A7
#define P2_DOWN   A6
#define P2_LEFT   A5
#define P2_RIGHT  A4
#define P2_FIRE   A1
#define P2_INA    A3
#define P2_INB    A2

char input[128];
int index = 0;
bool p1_buttons[] = {true, true, true, true, true};

void setup() {
  pinMode(P1_UP,OUTPUT);
  pinMode(P1_DOWN,OUTPUT);
  pinMode(P1_LEFT,OUTPUT);
  pinMode(P1_RIGHT,OUTPUT);
  pinMode(P1_FIRE,OUTPUT);
  pinMode(P1_INA,OUTPUT);
  pinMode(P1_INB,OUTPUT);
  pinMode(P2_UP,OUTPUT);
  pinMode(P2_DOWN,OUTPUT);
  pinMode(P2_LEFT,OUTPUT);
  pinMode(P2_RIGHT,OUTPUT);
  pinMode(P2_FIRE,OUTPUT);
  pinMode(P2_INA,OUTPUT);
  pinMode(P2_INB,OUTPUT);
  digitalWrite(P1_UP, HIGH);
  digitalWrite(P1_DOWN, HIGH);
  digitalWrite(P1_LEFT, HIGH);
  digitalWrite(P1_RIGHT, HIGH);
  digitalWrite(P1_FIRE, HIGH);
  digitalWrite(P1_INA, LOW);
  digitalWrite(P1_INB, LOW);
  Serial.begin(115200);
}

void loop() {
  digitalWrite(P1_UP, p1_buttons[0]);
  digitalWrite(P1_DOWN, p1_buttons[1]);
  digitalWrite(P1_LEFT, p1_buttons[2]);
  digitalWrite(P1_RIGHT, p1_buttons[3]);
  digitalWrite(P1_FIRE, p1_buttons[4]);
}

void serialEvent() {
  while (Serial.available()) {
    char inChar = (char)Serial.read();
    if (inChar == '\n' || inChar == '\0') {
      if(input[0] == '1') { p1_buttons[0] = false; } else { p1_buttons[0] = true; }
      if(input[1] == '1') { p1_buttons[1] = false; } else { p1_buttons[1] = true; }
      if(input[2] == '1') { p1_buttons[2] = false; } else { p1_buttons[2] = true; }
      if(input[3] == '1') { p1_buttons[3] = false; } else { p1_buttons[3] = true; }
      if(input[4] == '1') { p1_buttons[4] = false; } else { p1_buttons[4] = true; }
      index = 0;
    } else {
      input[index] = inChar;
      index++;
      if(index >= 127) index = 127;
    }
  }
}
