R = load('R.txt');
Y = load('Y.txt');

[Ynorm, Ymean] = normalizeRatings(Y, R);

num_users = size(Y, 2);
num_repos = size(Y, 1);
num_features = 10;

X = randn(num_repos, num_features);
Theta = randn(num_users, num_features);

initial_parameters = [X(:); Theta(:)];

options = optimset('GradObj', 'on', 'MaxIter', 100);

lambda = 10;
theta = fmincg (@(t)(cofiCostFunc(t, Y, R, num_users, num_repos, ...
                                num_features, lambda)), ...
                initial_parameters, options);
            
X = reshape(theta(1:num_repos*num_features), num_repos, num_features);
Theta = reshape(theta(num_repos*num_features+1:end), ...
                num_users, num_features);

p = X * Theta';
p(p < 0) = 0;
p_2 = round(p);

dlmwrite('P.txt', p_2);