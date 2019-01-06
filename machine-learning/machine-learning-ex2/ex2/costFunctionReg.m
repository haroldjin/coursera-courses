function [J, grad] = costFunctionReg(theta, X, y, lambda)
%COSTFUNCTIONREG Compute cost and gradient for logistic regression with regularization
%   J = COSTFUNCTIONREG(theta, X, y, lambda) computes the cost of using
%   theta as the parameter for regularized logistic regression and the
%   gradient of the cost w.r.t. to the parameters.

% Initialize some useful values
m = length(y); % number of training examples


% You need to return the following variables correctly
J = 0;
grad = zeros(size(theta));

% ====================== YOUR CODE HERE ======================
% Instructions: Compute the cost of a particular choice of theta.
%               You should set J to the cost.
%               Compute the partial derivatives and set grad to the partial
%               derivatives of the cost w.r.t. each parameter in theta

% set h theta of x vector
h = sigmoid(X * theta);
theta_exclude_1st_column = [0;theta(2:end,:)];
reg = lambda*(theta_exclude_1st_column' * theta_exclude_1st_column)/(2*m);
J = ((-y)' * log(h) - (1-y)' * log(1-h))/m + reg;
grad = (X' * (h-y) + lambda*theta_exclude_1st_column)/m;

%% Not needed any more
% cost = (h-y)';
% grad_j_EQ1 = 1./m .* cost * X(:,1);
% grad_j_G1 = 1./m .* cost * X(:,2:end) + (lambda/m*theta(2:end,1))';
% grad = [grad_j_EQ1 grad_j_G1]';


% =============================================================

end
