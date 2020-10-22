LIBRARY ieee;
USE ieee.std_logic_1164.all;
USE ieee.std_logic_signed.all;
USE ieee.numeric_std.all;

ENTITY testalu4 IS
END testalu4;

ARCHITECTURE test_b OF testalu4 IS
  COMPONENT ALU4 IS PORT (
      ALUin1: IN STD_LOGIC_VECTOR(3 DOWNTO 0);
      ALUin2: IN STD_LOGIC_VECTOR(3 DOWNTO 0);
      ALUctrl: IN STD_LOGIC_VECTOR(3 DOWNTO 0);
      ALUout1: OUT STD_LOGIC_VECTOR(3 DOWNTO 0);
      zero: OUT STD_LOGIC);
  END COMPONENT;
  
  SIGNAL ALUin1 : STD_LOGIC_VECTOR(3 DOWNTO 0) := (others => '0');
  SIGNAL ALUin2 : STD_LOGIC_VECTOR(3 DOWNTO 0)  := (others => '0');
  SIGNAL ALUctrl : STD_LOGIC_VECTOR(3 DOWNTO 0)  := (others => '0');
  SIGNAL ALUout1: STD_LOGIC_VECTOR(3 DOWNTO 0);
  SIGNAL zero: STD_LOGIC;

  BEGIN
    AL1: ALU4 PORT MAP(ALUin1=>ALUin1,ALUin2=>ALUin2,ALUctrl=>ALUctrl,ALUout1=>ALUout1,zero=>zero);
    PROCESS
      BEGIN
        ALUin1<="0010";ALUin2<="0100";ALUctrl<="0010";WAIT FOR 50ps;
        ALUin1<="0100";ALUin2<="1111";ALUctrl<="0000";WAIT FOR 50ps;
        ALUin1<="0100";ALUin2<="1111";ALUctrl<="0001";WAIT FOR 50ps;
        ALUin1<="0100";ALUin2<="0010";ALUctrl<="0110";WAIT FOR 50ps;
        ALUin1<="0100";ALUin2<="0110";ALUctrl<="0110";WAIT FOR 50ps;
    END PROCESS;
END test_b;