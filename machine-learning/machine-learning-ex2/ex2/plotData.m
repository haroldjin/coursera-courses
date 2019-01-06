function plotData(X, y)
%PLOTDATA Plots the data points X and y into a new figure
%   PLOTDATA(x,y) plots the data points with + for the positive examples
%   and o for the negative examples. X is assumed to be a Mx2 matrix.

% Create New Figure
figure; hold on;

% ====================== YOUR CODE HERE ======================
% Instructions: Plot the positive and negative examples on a
%               2D plot, using the option 'k+' for the positive
%               examples and 'ko' for the negative examples.
%

% create indexes vector where the value of y of a specific row == 1 to pos var and row == 0 to neg var
pos = find(y==1); neg = find(y == 0);

% Plot positive y positions on x with data points of column 1 and 2 on a chart. lineWidth 2 to make line thicker and marker size 7 to make it larger
plot(X(pos, 1), X(pos, 2), 'k+','LineWidth', 2, 'MarkerSize', 7);
plot(X(neg, 1), X(neg, 2), 'ko', 'MarkerFaceColor', 'y','MarkerSize', 7);

% =========================================================================

hold off;
end
