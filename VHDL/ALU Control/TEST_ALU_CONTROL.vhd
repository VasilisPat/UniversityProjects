LIBRARY ieee;
USE ieee.std_logic_1164.all;
USE ieee.std_logic_signed.all;

ENTITY TEST_ALU_CONTROL IS PORT (
  OP_5to0: IN STD_LOGIC_VECTOR(5 DOWNTO 0);
  ALU_op: IN STD_LOGIC_VECTOR(1 DOWNTO 0);
  ALUin1: IN STD_LOGIC_VECTOR(3 DOWNTO 0);
  ALUin2: IN STD_LOGIC_VECTOR(3 DOWNTO 0);
  ALUout1: OUT STD_LOGIC_VECTOR(3 DOWNTO 0);
  zero: OUT STD_LOGIC);
END TEST_ALU_CONTROL;

ARCHITECTURE behav OF TEST_ALU_CONTROL IS
  
  COMPONENT ALU_Control IS PORT (
    OP_5to0: IN STD_LOGIC_VECTOR(5 DOWNTO 0);
    ALU_op: IN STD_LOGIC_VECTOR(1 DOWNTO 0);
    Operation: OUT STD_LOGIC_VECTOR(3 DOWNTO 0));
  END COMPONENT;
  
  COMPONENT ALU4 IS PORT (
    ALUin1: IN STD_LOGIC_VECTOR(3 DOWNTO 0);
    ALUin2: IN STD_LOGIC_VECTOR(3 DOWNTO 0);
    ALUctrl: IN STD_LOGIC_VECTOR(3 DOWNTO 0);
    ALUout1: OUT STD_LOGIC_VECTOR(3 DOWNTO 0);
    zero: OUT STD_LOGIC);
  END COMPONENT;
  
  SIGNAL CTRLOP: STD_LOGIC_VECTOR(3 DOWNTO 0);
  
BEGIN
 ALC: ALU_Control PORT MAP(OP_5to0=>OP_5to0,ALU_op=>ALU_op,Operation=>CTRLOP);
 AL: ALU4 PORT MAP(ALUin1=>ALUin1,ALUin2=>ALUin2,ALUctrl=>CTRLOP,ALUout1=>ALUout1,zero=>zero);
END behav;