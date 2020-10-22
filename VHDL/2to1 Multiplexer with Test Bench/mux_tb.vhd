ENTITY test_mux IS
END test_mux;

ARCHITECTURE test_b OF test_mux IS
SIGNAL A1,B1,S1,C1:bit;
COMPONENT mux2to1 PORT (a,b,s: IN bit; c: OUT bit);
END COMPONENT;

BEGIN
 M1:mux2to1 PORT MAP (a=>A1,b=>B1,s=>S1,c=>C1);
 PROCESS
  BEGIN
   A1<='1';B1<='0';S1<='1';WAIT FOR 20 ps;
   A1<='1';B1<='0';S1<='0';WAIT FOR 20 ps;
  END PROCESS;
END test_b;