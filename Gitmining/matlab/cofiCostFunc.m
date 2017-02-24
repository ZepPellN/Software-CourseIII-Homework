function [J, grad] = cofiCostFunc(params, Y, R, num_users, nums_repos, ...
                                  num_features, lambda)

X = reshape(params(1:nums_repos*num_features), nums_repos, num_features);
Theta = reshape(params(nums_repos*num_features+1:end), ...
                num_users, num_features);

X_grad = zeros(size(X));
Theta_grad = zeros(size(Theta));

J = sum(sum((X * Theta' - Y) .^ 2 .* R)) / 2 + lambda / 2 * (sum(sum(Theta .^ 2)) + sum(sum(X .^ 2)));
for i = 1 : nums_repos
    X_grad(i,:) = ((X(i,:) * Theta' - Y(i,:)) .* R(i,:)) * Theta + lambda * X(i,:);
end

for j = 1 : num_users
   Theta_grad(j,:) = ((X * Theta(j,:)' - Y(:,j)) .* R(:,j))' * X + lambda * Theta(j,:);
end


% =============================================================

grad = [X_grad(:); Theta_grad(:)];

end
