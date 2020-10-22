LIBRARY ieee;
USE ieee.std_logic_1164.all;
USE ieee.std_logic_signed.all;
USE ieee.numeric_std.all;

ENTITY ALU4 IS PORT (
  ALUin1: IN STD_LOGIC_VECTOR(3 DOWNTO 0);
  ALUin2: IN STD_LOGIC_VECTOR(3 DOWNTO 0);
  ALUctrl: IN STD_LOGIC_VECTOR(3 DOWNTO 0);
  ALUout1: OUT STD_LOGIC_VECTOR(3 DOWNTO 0);
  zero: OUT STD_LOGIC);
END ALU4;

ARCHITECTURE behav OF ALU4 IS
  SIGNAL temp_res : STD_LOGIC_VECTOR(3 DOWNTO 0);
BEGIN
  PROCESS (ALUin1,ALUin2,ALUctrl)
    BEGIN
      CASE (ALUctrl) IS
      WHEN "0000" =>
        temp_res <= ALUin1 and ALUin2; 
      WHEN "0001" =>
        temp_res <= ALUin1 or ALUin2;
      WHEN "0010" =>
        temp_res <= ALUin1 + ALUin2;
      WHEN "0110" =>
        temp_res <= ALUin1 - ALUin2;
      WHEN "0111" =>
        IF((ALUin1 - ALUin2)<0) THEN
          temp_res <= "0001";
        ELSE
          temp_res <= "0000";
        END IF;
      WHEN OTHERS =>
        temp_res <= "0000";
      END CASE;
      IF temp_res = "0000" THEN
        zero <= '1';
      ELSE
        zero <= '0';
      END IF;
  END PROCESS;
  ALUout1 <= temp_res;
END behav;