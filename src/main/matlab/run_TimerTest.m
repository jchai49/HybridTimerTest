%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
% Matlab M-file       Project: TimerTest  @ Hybrid Systems Lab
%
% https://hybrid.soe.ucsc.edu/
%
% Filename: run_timerTest
%
% Author : Jun Chai
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
clc
clear

%% initial conditions
    i0 = 0; 
    tau0 = 0;
    h0 = 0;
    x0 = [i0; tau0; h0];
 
% %% Initialize control
global V 
    V = 0;

%% simulation horizon
TSPAN=[0 20];
JSPAN = [0 1e5];

% rule for jumps
% rule = 1 -> priority for jumps
% rule = 2 -> priority for flows
rule = 1;

%solver tolerances
options = odeset('RelTol',1e-3,'MaxStep',1e-3);
%% simulate
[t j x] = HyEQsolver(@f_timer,@g_timer,@C_timer,@D_timer,x0,...
    TSPAN,JSPAN,rule,options);
save timer

%% the flow set C
function value = C_timer(x)
    value = 1;
end

%% the flow map
function xdot = f_timer(x)
xdot = [0; 1; 0];
end

%% the jump set D
function inside = D_timer(x) 
% states
    i = x(1);
    tau = x(2);
    h = x(3);

% control value
global V
    V = floor(tau)*(1 + floor(tau))/2;
    
    if (floor(tau) > h) && (i < V || i == 0) 
        inside = 1;
    else
        inside = 0;
    end		
end

%% the jump map
 function xplus = g_timer(x) 
% states
    i = x(1);
    tau = x(2);
    h = x(3);

% control value from D
global V
    V = floor(tau)*(1 + floor(tau))/2;
    
    iplus = i + 1;
    
    if iplus == V
        hplus = floor(tau);
    else
        hplus = h;
    end
    
xplus = [iplus; tau; hplus];
end