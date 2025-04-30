// API Configuration
const API_BASE_URL = 'http://localhost:8080/api';

// DOM Elements
const featuredCollections = document.getElementById('featuredCollections');

// Fetch featured items
document.addEventListener('DOMContentLoaded', function() {
    fetchFeaturedItems();
});

async function fetchFeaturedItems() {
    try {
        const response = await fetch(API_BASE_URL);
        
        if (!response.ok) {
            throw new Error('Network response was not ok');
        }
        
        const items = await response.json();
        displayFeaturedItems(items.slice(0, 4)); // Show first 4 as featured
    } catch (error) {
        console.error('Error fetching featured items:', error);
        featuredCollections.innerHTML = `
            <div class="error">
                <p>Failed to load featured items. Please try again later.</p>
                <p>${error.message}</p>
            </div>
        `;
    }
}

function displayFeaturedItems(items) {
    featuredCollections.innerHTML = items.map(item => `
        <div class="collection-card">
            <img src="images/${item.Item_id}.jpg" alt="${item.Item_name}" class="collection-img">
            <div class="collection-info">
                <h3>${item.Item_name}</h3>
                <p>${item.Item_type}</p>
                <p class="collection-price">$${item.Item_price.toFixed(2)}</p>
                <a href="product-detail.html?id=${item.Item_id}" class="btn btn-primary">View Details</a>
            </div>
        </div>
    `).join('');
}

// Mobile menu toggle
document.getElementById('menuBtn').addEventListener('click', function() {
    const navLinks = document.querySelector('.nav-links');
    navLinks.classList.toggle('active');
});