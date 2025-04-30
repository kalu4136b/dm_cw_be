const apiUrl = '/api/items';

document.addEventListener('DOMContentLoaded', () => {
    loadItems();

    const form = document.getElementById('item-form');
    form.addEventListener('submit', saveItem);
});

function loadItems() {
    fetch(apiUrl)
        .then(response => response.json())
        .then(items => {
            const tbody = document.querySelector('#items-table tbody');
            tbody.innerHTML = '';

            items.forEach(item => {
                const row = document.createElement('tr');

                row.innerHTML = `
                    <td>${item.item_id}</td>
                    <td>${item.item_name}</td>
                    <td>${item.item_type}</td>
                    <td>${item.item_description}</td>
                    <td>${item.item_price}</td>
                    <td>${item.gem_Count}</td>
                    <td>${item.gem_Type}</td>
                    <td>
                        <button class="action-btn edit-btn" onclick="editItem(${item.item_id})">Edit</button>
                        <button class="action-btn delete-btn" onclick="deleteItem(${item.item_id})">Delete</button>
                    </td>
                `;

                tbody.appendChild(row);
            });
        });
}

function saveItem(e) {
    e.preventDefault();

    const id = document.getElementById('item-id').value;
    const item = {
        item_name: document.getElementById('item-name').value,
        item_type: document.getElementById('item-type').value,
        item_description: document.getElementById('item-description').value,
        item_price: parseFloat(document.getElementById('item-price').value),
        gem_Count: parseInt(document.getElementById('gem-count').value),
        gem_Type: document.getElementById('gem-type').value
    };

    if (id) {
        // Update existing item
        fetch(`${apiUrl}/${id}`, {
            method: 'PUT',
            headers: {'Content-Type': 'application/json'},
            body: JSON.stringify(item)
        }).then(() => {
            resetForm();
            loadItems();
        });
    } else {
        // Add new item
        fetch(apiUrl, {
            method: 'POST',
            headers: {'Content-Type': 'application/json'},
            body: JSON.stringify(item)
        }).then(() => {
            resetForm();
            loadItems();
        });
    }
}

function editItem(id) {
    fetch(`${apiUrl}/${id}`)
        .then(response => response.json())
        .then(item => {
            document.getElementById('item-id').value = item.item_id;
            document.getElementById('item-name').value = item.item_name;
            document.getElementById('item-type').value = item.item_type;
            document.getElementById('item-description').value = item.item_description;
            document.getElementById('item-price').value = item.item_price;
            document.getElementById('gem-count').value = item.gem_Count;
            document.getElementById('gem-type').value = item.gem_Type;
        });
}

function deleteItem(id) {
    if (confirm('Are you sure you want to delete this item?')) {
        fetch(`${apiUrl}/${id}`, { method: 'DELETE' })
            .then(() => loadItems());
    }
}

function resetForm() {
    document.getElementById('item-form').reset();
    document.getElementById('item-id').value = '';
}
