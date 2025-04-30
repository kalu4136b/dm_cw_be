// auth.js - Authentication functionality for login and registration

// DOM Elements
const loginForm = document.getElementById('loginForm');
const registerForm = document.getElementById('registerForm');
const passwordInput = document.getElementById('password');
const confirmPasswordInput = document.getElementById('confirmPassword');
const strengthMeter = document.getElementById('strengthMeter');
const passwordHints = document.querySelector('.password-hints');

// API Endpoints
const API_BASE_URL = 'http://localhost:8080/api';
const LOGIN_URL = `${API_BASE_URL}/users/login`;
const REGISTER_URL = `${API_BASE_URL}/users/register`;

// Password strength indicators
const strengthLevels = [
    { color: '#e74c3c', text: 'Very Weak' },
    { color: '#f39c12', text: 'Weak' },
    { color: '#f1c40f', text: 'Moderate' },
    { color: '#2ecc71', text: 'Strong' },
    { color: '#27ae60', text: 'Very Strong' }
];

// Initialize auth functionality
document.addEventListener('DOMContentLoaded', function() {
    // Initialize login form if it exists
    if (loginForm) {
        loginForm.addEventListener('submit', handleLogin);
    }

    // Initialize registration form if it exists
    if (registerForm) {
        registerForm.addEventListener('submit', handleRegister);

        // Password strength meter
        if (passwordInput) {
            passwordInput.addEventListener('input', updatePasswordStrength);
        }

        // Password confirmation validation
        if (confirmPasswordInput) {
            confirmPasswordInput.addEventListener('input', validatePasswordMatch);
        }
    }
});

/**
 * Handle login form submission
 */
async function handleLogin(e) {
    e.preventDefault();

    const formData = new FormData(loginForm);
    const data = {
        username: formData.get('email'),
        password: formData.get('password')
    };

    try {
        // Show loading state
        const submitBtn = loginForm.querySelector('button[type="submit"]');
        const originalBtnText = submitBtn.textContent;
        submitBtn.innerHTML = '<i class="fas fa-spinner fa-spin"></i> Signing In...';
        submitBtn.disabled = true;

        const response = await fetch(LOGIN_URL, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(data)
        });

        const result = await response.json();

        if (!response.ok) {
            throw new Error(result.message || 'Login failed');
        }

        // Store the authentication token and user data
        localStorage.setItem('authToken', result.token);
        localStorage.setItem('user', JSON.stringify(result.user));

        // Show success message
        showNotification('Login successful! Redirecting...', 'success');

        // Redirect to appropriate page
        setTimeout(() => {
            window.location.href = result.user.role === 'ADMIN' ?
                '/admin/dashboard.html' : '/index.html';
        }, 1500);

    } catch (error) {
        console.error('Login error:', error);
        showNotification(error.message || 'Login failed. Please try again.', 'error');

        // Reset button state
        const submitBtn = loginForm.querySelector('button[type="submit"]');
        submitBtn.textContent = originalBtnText;
        submitBtn.disabled = false;
    }
}

/**
 * Handle registration form submission
 */
async function handleRegister(e) {
    e.preventDefault();

    if (!validateRegistrationForm()) {
        return;
    }

    const formData = new FormData(registerForm);
    const data = {
        username: formData.get('email'),
        email: formData.get('email'),
        password: formData.get('password'),
        role: 'USER' // Default role for new users
    };

    try {
        // Show loading state
        const submitBtn = registerForm.querySelector('button[type="submit"]');
        const originalBtnText = submitBtn.textContent;
        submitBtn.innerHTML = '<i class="fas fa-spinner fa-spin"></i> Creating Account...';
        submitBtn.disabled = true;

        const response = await fetch(REGISTER_URL, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(data)
        });

        const result = await response.json();

        if (!response.ok) {
            throw new Error(result.message || 'Registration failed');
        }

        // Show success message
        showNotification('Registration successful! You can now login.', 'success');

        // Redirect to login page
        setTimeout(() => {
            window.location.href = '/login.html';
        }, 1500);

    } catch (error) {
        console.error('Registration error:', error);
        showNotification(error.message || 'Registration failed. Please try again.', 'error');

        // Reset button state
        const submitBtn = registerForm.querySelector('button[type="submit"]');
        submitBtn.textContent = originalBtnText;
        submitBtn.disabled = false;
    }
}

/**
 * Validate registration form
 */
function validateRegistrationForm() {
    const password = passwordInput.value;
    const confirmPassword = confirmPasswordInput.value;
    const agreeTerms = document.getElementById('agreeTerms').checked;

    // Check password match
    if (password !== confirmPassword) {
        showNotification('Passwords do not match', 'error');
        confirmPasswordInput.focus();
        return false;
    }

    // Check password strength
    const strength = calculatePasswordStrength(password);
    if (strength < 2) { // Require at least "Moderate" strength
        showNotification('Please choose a stronger password', 'error');
        passwordInput.focus();
        return false;
    }

    // Check terms agreement
    if (!agreeTerms) {
        showNotification('You must agree to the terms and conditions', 'error');
        return false;
    }

    return true;
}

/**
 * Calculate password strength
 */
function calculatePasswordStrength(password) {
    let strength = 0;

    // Length check
    if (password.length >= 8) strength++;
    if (password.length >= 12) strength++;

    // Complexity checks
    if (/[A-Z]/.test(password)) strength++;
    if (/[0-9]/.test(password)) strength++;
    if (/[^A-Za-z0-9]/.test(password)) strength++;

    // Cap at max strength level
    return Math.min(strength, strengthLevels.length - 1);
}

/**
 * Update password strength meter
 */
function updatePasswordStrength() {
    const password = passwordInput.value;
    const strength = calculatePasswordStrength(password);

    // Update strength meter
    strengthMeter.style.width = `${(strength + 1) * 20}%`;
    strengthMeter.style.backgroundColor = strengthLevels[strength].color;

    // Update hints
    if (password.length > 0) {
        passwordHints.innerHTML = `
            <p>Password strength: <strong>${strengthLevels[strength].text}</strong></p>
            ${password.length < 8 ? '<p class="error">Must be at least 8 characters</p>' : ''}
            ${!/[A-Z]/.test(password) ? '<p class="error">Include uppercase letters</p>' : ''}
            ${!/[0-9]/.test(password) ? '<p class="error">Include numbers</p>' : ''}
        `;
    } else {
        passwordHints.innerHTML = `
            <p>Password must contain at least 8 characters, including uppercase, lowercase, and numbers.</p>
        `;
    }
}

/**
 * Validate password match
 */
function validatePasswordMatch() {
    const password = passwordInput.value;
    const confirmPassword = confirmPasswordInput.value;

    if (confirmPassword.length > 0 && password !== confirmPassword) {
        confirmPasswordInput.style.borderColor = '#e74c3c';
    } else {
        confirmPasswordInput.style.borderColor = '#ddd';
    }
}

/**
 * Check if user is authenticated
 */
function checkAuth() {
    const authToken = localStorage.getItem('authToken');
    const user = JSON.parse(localStorage.getItem('user'));

    if (!authToken || !user) {
        return null;
    }

    // Verify token expiration if using JWT
    // (Implementation depends on your backend)

    return user;
}

/**
 * Logout user
 */
function logout() {
    localStorage.removeItem('authToken');
    localStorage.removeItem('user');
    window.location.href = '/login.html';
}

/**
 * Protect routes that require authentication
 */
function protectRoute(requiredRole = null) {
    const user = checkAuth();

    if (!user) {
        window.location.href = '/login.html';
        return;
    }

    if (requiredRole && user.role !== requiredRole) {
        window.location.href = '/unauthorized.html';
        return;
    }

    return user;
}

// Make functions available globally if needed
window.auth = {
    checkAuth,
    logout,
    protectRoute
};