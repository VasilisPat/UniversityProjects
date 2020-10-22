LIBRARY ieee;
USE ieee.std_logic_1164.all;

ENTITY ALU_Control IS PORT (
  OP_5to0: IN STD_LOGIC_VECTOR(5 DOWNTO 0);
  ALU_op: IN STD_LOGIC_VECTOR(1 DOWNTO 0);
  Operation: OUT STD_LOGIC_VECTOR(3 DOWNTO 0));
END ALU_Control;

ARCHITECTURE behav OF ALU_Control IS
  SIGNAL tmpALUControl_func : STD_LOGIC_VECTOR(3 DOWNTO 0) := (OTHERS=>'0');
  SIGNAL tmpALUControl_op : STD_LOGIC_VECTOR(3 DOWNTO 0) := (OTHERS=>'0');
BEGIN
  WITH OP_5to0 SELECT
    tmpALUControl_func <=   "0110" WHEN "100010",
                            "0000" WHEN "100100",
                            "0010" WHEN "100000",
                            "0001" WHEN "100101",
                            "1111" WHEN OTHERS;
  WITH ALU_op SELECT 
    tmpALUControl_op <= "0010" WHEN "00",
                        "0110" WHEN "01",
                        "1111" WHEN OTHERS;
  WITH ALU_op SELECT
    Operation <= tmpALUControl_func WHEN "10",
                 tmpALUControl_op WHEN OTHERS;        
END behav;