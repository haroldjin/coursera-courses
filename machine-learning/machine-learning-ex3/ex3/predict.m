function p = predict(Theta1, Theta2, X)
%PREDICT Predict the label of an input given a trained neural network
%   p = PREDICT(Theta1, Theta2, X) outputs the predicted label of X given the
%   trained weights of a neural network (Theta1, Theta2)

% Useful values
m = size(X, 1);
num_labels = size(Theta2, 1);

% You need to return the following variables correctly
p = zeros(size(X, 1), 1);

% ====================== YOUR CODE HERE ======================
% Instructions: Complete the following code to make predictions using
%               your learned neural network. You should set p to a
%               vector containing labels between 1 to num_labels.
%
% Hint: The max function might come in useful. In particular, the max
%       function can also return the index of the max element, for more
%       information see 'help max'. If your examples are in rows, then, you
%       can use max(A, [], 2) to obtain the max for each row.
%

% g(Z2)
%num_labels
%size(Theta2)
%pause;

% X = a , a * theta1
nX = [ones(m,1) X];
for i = 1:m
    % Get individual row's score and make a matrix
    % layer 1 => 25 * 401
    z2 = sum(Theta1*nX(i,:)',2);
    % z2 = sum(nX(i,:) .* Theta1, 2);

    % layer 2 => 26 x 1 a2
    a2 = [1; sigmoid(z2)];

    % 
    z3 = sum(a2' .* Theta2,2);
    a3 = sigmoid(z3);
    %for j : 1:size(a2,1)
    %    z3 = sum(X())
    [val, ind] = max(a3);
    p(i) = ind;

% =========================================================================


end