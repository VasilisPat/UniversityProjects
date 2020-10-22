LIBRARY ieee;
USE ieee.std_logic_1164.all;
USE ieee.std_logic_unsigned.all;

ENTITY upcount IS PORT(
  Clear, Clock : IN STD_LOGIC ;
  Q : OUT STD_LOGIC_VECTOR(1 DOWNTO 0)) ;
END upcount ;

ARCHITECTURE behav OF upcount IS
  SIGNAL COUNT:STD_LOGIC_VECTOR(1 DOWNTO 0); 
BEGIN
  PROCESS(Clock,Clear)
  BEGIN
    IF(Clock'EVENT AND Clock='1') THEN
      IF(Clear='1') THEN
        COUNT<="00";
      ELSE
        COUNT<= COUNT + 1;
      END IF;
    END IF;
  END PROCESS;
  Q<=COUNT;
END behav;
  