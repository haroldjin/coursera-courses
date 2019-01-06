function [theta, J_history] = gradientDescent(X, y, theta, alpha, num_iters)
%GRADIENTDESCENT Performs gradient descent to learn theta
%   theta = GRADIENTDESCENT(X, y, theta, alpha, num_iters) updates theta by
%   taking num_iters gradient steps with learning rate alpha

% Initialize some useful values
m = length(y); % number of training examples
J_history = zeros(num_iters, 1);

for iter = 1:num_iters
    % Vectorized errors => X * theta - y => n x 1 => cost for each Xi using current theta in a vector
    % Vectorized errors' * X => 1 x n * n x 2 => Summation 1:m each errori * Xj for all X at j index as to calculate total changes attributed to theta j
    % Prev 1 x 2 => 1, total error X1, 2, total error X2
    % theta - alpha / m * Prev' => transpose to 2 x 1, so the theta can be deducted from the theta j changes
    % The goal is to update theta with smaller error rate for each theta j
    theta = theta - alpha / m * ((X * theta - y)' * X)';

    % Save the cost J in every iteration
    J_history(iter) = computeCost(X, y, theta);
end
end
