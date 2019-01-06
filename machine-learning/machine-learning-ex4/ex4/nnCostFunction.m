function [J grad] = nnCostFunction(nn_params, ...
                                   input_layer_size, ...
                                   hidden_layer_size, ...
                                   num_labels, ...
                                   X, y, lambda)
%NNCOSTFUNCTION Implements the neural network cost function for a two layer
%neural network which performs classification
%   [J grad] = NNCOSTFUNCTON(nn_params, hidden_layer_size, num_labels, ...
%   X, y, lambda) computes the cost and gradient of the neural network. The
%   parameters for the neural network are "unrolled" into the vector
%   nn_params and need to be converted back into the weight matrices.
%
%   The returned parameter grad should be a "unrolled" vector of the
%   partial derivatives of the neural network.
%

% Reshape nn_params back into the parameters Theta1 and Theta2, the weight matrices
% for our 2 layer neural network
Theta1 = reshape(nn_params(1:hidden_layer_size * (input_layer_size + 1)), ...
                 hidden_layer_size, (input_layer_size + 1));

Theta2 = reshape(nn_params((1 + (hidden_layer_size * (input_layer_size + 1))):end), ...
                 num_labels, (hidden_layer_size + 1));

% Setup some useful variables
m = size(X, 1);

% You need to return the following variables correctly
J = 0;
Theta1_grad = zeros(size(Theta1));
Theta2_grad = zeros(size(Theta2));

% ====================== YOUR CODE HERE ======================
% Instructions: You should complete the code by working through the
%               following parts.
%
% Part 1: Feedforward the neural network and return the cost in the
%         variable J. After implementing Part 1, you can verify that your
%         cost function computation is correct by verifying the cost
%         computed in ex4.m
%
% Part 2: Implement the backpropagation algorithm to compute the gradients
%         Theta1_grad and Theta2_grad. You should return the partial derivatives of
%         the cost function with respect to Theta1 and Theta2 in Theta1_grad and
%         Theta2_grad, respectively. After implementing Part 2, you can check
%         that your implementation is correct by running checkNNGradients
%
%         Note: The vector y passed into the function is a vector of labels
%               containing values from 1..K. You need to map this vector into a
%               binary vector of 1's and 0's to be used with the neural network
%               cost function.
%
%         Hint: We recommend implementing backpropagation using a for-loop
%               over the training examples if you are implementing it for the
%               first time.
%
% Part 3: Implement regularization with the cost function and gradients.
%
%         Hint: You can implement this around the code for
%               backpropagation. That is, you can compute the gradients for
%               the regularization separately and then add them to Theta1_grad
%               and Theta2_grad from Part 2.
%

% Add ones to first column of X
% X => 5000 * 401, Theta1' => 401 * 25, => 5000 * 25
X = [ones(m, 1) X];
Z2 = X * Theta1';
A2 = sigmoid(Z2);

% Add ones to the first unit of A2 => 5000 * 26
% A2 => 5000 * 26, Theta2' => 26 * 10, => 5000 * 10
A2 = [ones(m, 1) A2];
Z3 = A2 * Theta2';
h = sigmoid(Z3);

% log(h) => 5000 * 10, yv => 10 * 1
for i = 1:m
    yv = ([1:num_labels] == y(i))';
    errl3 = h(i,:)' - yv;

    % hidden layer % Theta2' => 26 * 10, errl3 => 10 * 1, sigmoidGradient.. => 26 * 1
    errl2 = (Theta2' * errl3 .* [1; sigmoidGradient(Z2(i,:)')])(2:end);
    Theta2_grad = Theta2_grad + (errl3 * A2(i,:));
    Theta1_grad = Theta1_grad + (errl2 * X(i,:));

    % Finding Cost function J
    ps = sum((log(h(i,:)) * (-yv) - log(1-h(i,:)) * (1-yv)));
    J = J + ps;
end
J = J/m;

% Finding cost
ThetaR1S = sum((Theta1(:, 2:end).^2)(:));
ThetaR2S = sum((Theta2(:, 2:end).^2)(:));
J = J + lambda * (ThetaR1S + ThetaR2S)/(2*m);

% Finding gradient
Theta1_grad = Theta1_grad/m + lambda * [zeros(size(Theta1,1),1) Theta1( :,2:end )]/m;
Theta2_grad = Theta2_grad/m + lambda * [zeros(size(Theta2,1),1) Theta2( :,2:end )]/m;

grad = [Theta1_grad(:) ; Theta2_grad(:)];


end
