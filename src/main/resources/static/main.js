// main.js - Common JavaScript for all pages

// DOM Elements
const menuBtn = document.getElementById('menuBtn');
const navLinks = document.querySelector('.nav-links');
const currentYear = document.querySelector('#current-year');

// API Configuration
const API_BASE_URL = 'http://localhost:8080/api';

// Initialize common functionality when DOM is loaded
document.addEventListener('DOMContentLoaded', function() {
    // Set current year in footer
    if (currentYear) {
        currentYear.textContent = new Date().getFullYear();
    }

    // Mobile menu toggle
    if (menuBtn && navLinks) {
        menuBtn.addEventListener('click', toggleMobileMenu);
    }

    // Check if user is logged in
    checkLoginStatus();

    // Add smooth scrolling to all links
    document.querySelectorAll('a[href^="#"]').forEach(anchor => {
        anchor.addEventListener('click', smoothScroll);
    });
});

// Toggle mobile menu
function toggleMobileMenu() {
    navLinks.classList.toggle('active');
    menuBtn.innerHTML = navLinks.classList.contains('active') ? 
        '<i class="fas fa-times"></i>' : '<i class="fas fa-bars"></i>';
}

// Check if user is logged in
function checkLoginStatus() {
    const authToken = localStorage.getItem('authToken');
    const loginLinks = document.querySelectorAll('.auth-links');
    
    if (authToken) {
        loginLinks.forEach(link => {
            link.innerHTML = `
                <a href="#" id="logoutBtn">Logout</a>
                <a href="admin/dashboard.html">Dashboard</a>
            `;
        });
        
        document.getElementById('logoutBtn')?.addEventListener('click', logoutUser);
    }
}

// Logout user
function logoutUser() {
    localStorage.removeItem('authToken');
    localStorage.removeItem('user');
    window.location.href = 'index.html';
}

// Smooth scrolling for anchor links
function smoothScroll(e) {
    e.preventDefault();
    const targetId = this.getAttribute('href');
    if (targetId === '#') return;
    
    const targetElement = document.querySelector(targetId);
    if (targetElement) {
        window.scrollTo({
            top: targetElement.offsetTop - 80,
            behavior: 'smooth'
        });
    }
}

// Common API request function
async function makeRequest(url, method = 'GET', body = null, requiresAuth = false) {
    const headers = {
        'Content-Type': 'application/json'
    };

    if (requiresAuth) {
        const authToken = localStorage.getItem('authToken');
        if (authToken) {
            headers['Authorization'] = `Bearer ${authToken}`;
        }
    }

    const options = {
        method,
        headers,
        body: body ? JSON.stringify(body) : null
    };

    try {
        const response = await fetch(`${API_BASE_URL}${url}`, options);
        
        if (!response.ok) {
            const error = await response.json();
            throw new Error(error.message || 'Request failed');
        }

        return await response.json();
    } catch (error) {
        console.error('API request failed:', error);
        throw error;
    }
}

// Display notification to user
function showNotification(message, type = 'success') {
    const notification = document.createElement('div');
    notification.className = `notification ${type}`;
    notification.textContent = message;
    
    document.body.appendChild(notification);
    
    setTimeout(() => {
        notification.classList.add('fade-out');
        setTimeout(() => notification.remove(), 500);
    }, 3000);
}

// Form validation helper
function validateForm(form, requiredFields) {
    let isValid = true;
    
    requiredFields.forEach(field => {
        const input = form.querySelector(`[name="${field}"]`);
        if (!input.value.trim()) {
            input.classList.add('error');
            isValid = false;
        } else {
            input.classList.remove('error');
        }
    });
    
    return isValid;
}

// Close mobile menu when clicking outside
document.addEventListener('click', function(e) {
    if (navLinks?.classList.contains('active') && 
        !e.target.closest('.nav-links') && 
        !e.target.closest('#menuBtn')) {
        navLinks.classList.remove('active');
        menuBtn.innerHTML = '<i class="fas fa-bars"></i>';
    }
});