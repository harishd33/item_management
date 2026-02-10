# Deployment Guide - Item Management API

This guide provides step-by-step instructions for deploying the Item Management API to various cloud platforms.

## 📋 Pre-Deployment Checklist

Before deploying, ensure:
- ✅ Code is committed to a Git repository
- ✅ Application runs locally without errors (`mvn spring-boot:run`)
- ✅ All tests pass (`mvn test`)

---

## 🚀 Deployment Options

### Option 1: Railway (Recommended - Easiest)

Railway automatically detects and deploys Spring Boot applications.

#### Steps:

1. **Push code to GitHub:**
   ```bash
   git init
   git add .
   git commit -m "Item Management API"
   git branch -M main
   git remote add origin https://github.com/YOUR_USERNAME/item-management.git
   git push -u origin main
   ```

2. **Deploy on Railway:**
   - Visit [railway.app](https://railway.app)
   - Click "Start a New Project"
   - Select "Deploy from GitHub repo"
   - Authorize Railway to access your GitHub
   - Select your `item-management` repository
   - Railway will automatically detect Spring Boot
   - Wait 2-3 minutes for deployment

3. **Generate Public URL:**
   - Go to your project dashboard
   - Click "Settings" → "Networking"
   - Click "Generate Domain"
   - Your API will be available at: `https://your-app.railway.app`

4. **Test the deployment:**
   ```bash
   curl https://your-app.railway.app/api/items/health
   ```

**Railway automatically handles:**
- Java runtime detection
- Maven build process
- Port configuration
- Environment variables

---

### Option 2: Render (Free Tier Available)

Render offers a free tier for web services.

#### Steps:

1. **Create `render.yaml` configuration:**

Create this file in your project root:

```yaml
services:
  - type: web
    name: item-management-api
    env: java
    buildCommand: mvn clean package
    startCommand: java -jar target/item-management-0.0.1-SNAPSHOT.jar
    envVars:
      - key: JAVA_VERSION
        value: 21
      - key: PORT
        value: 8080
```

2. **Push to GitHub:**
   ```bash
   git add render.yaml
   git commit -m "Add Render configuration"
   git push
   ```

3. **Deploy on Render:**
   - Visit [render.com](https://render.com)
   - Click "New" → "Web Service"
   - Connect your GitHub repository
   - Render will detect the `render.yaml` file
   - Click "Create Web Service"
   - Wait 5-10 minutes for build and deployment

4. **Your app will be live at:**
   ```
   https://item-management-api.onrender.com
   ```

---

### Option 3: Heroku (Classic Option)

Heroku is a well-established platform but requires credit card verification (even for free tier).

#### Steps:

1. **Install Heroku CLI:**
   ```bash
   # Windows (using Chocolatey)
   choco install heroku-cli
   
   # Or download from: https://devcenter.heroku.com/articles/heroku-cli
   ```

2. **Create Procfile:**

Create a file named `Procfile` (no extension) in project root:

```
web: java -Dserver.port=$PORT -jar target/item-management-0.0.1-SNAPSHOT.jar
```

3. **Create system.properties:**

Create `system.properties` file:

```properties
java.runtime.version=21
```

4. **Deploy to Heroku:**
   ```bash
   # Login to Heroku
   heroku login
   
   # Create new app
   heroku create item-management-api
   
   # Add Java buildpack
   heroku buildpacks:set heroku/java
   
   # Deploy
   git add Procfile system.properties
   git commit -m "Add Heroku configuration"
   git push heroku main
   
   # Open your app
   heroku open
   ```

5. **Your app will be at:**
   ```
   https://item-management-api.herokuapp.com
   ```

---

### Option 4: Vercel (via Java Serverless)

⚠️ **Note:** Vercel is primarily for frontend apps. For Spring Boot, use Railway or Render instead.

However, if you must use Vercel, you'd need to convert to serverless functions, which is complex and not recommended for this project.

---

## 🔧 Configuration for Deployment

### Update application.properties for production:

Add these settings to `src/main/resources/application.properties`:

```properties
# Use PORT environment variable from cloud platform
server.port=${PORT:8080}

# Production settings
spring.application.name=item-management
server.error.include-message=always
logging.level.root=INFO
```

### Create application-prod.properties (optional):

For production-specific settings:

```properties
server.port=${PORT:8080}
logging.level.root=WARN
logging.level.com.demo.item_management=INFO
```

---

## 📝 Post-Deployment Steps

1. **Test your deployed API:**
   ```bash
   # Replace with your actual URL
   export API_URL="https://your-app.railway.app"
   
   # Health check
   curl $API_URL/api/items/health
   
   # Create an item
   curl -X POST $API_URL/api/items \
     -H "Content-Type: application/json" \
     -d '{
       "name": "Test Item",
       "description": "Testing deployed API",
       "category": "Test",
       "price": 99.99
     }'
   
   # Get the item (use the ID from previous response)
   curl $API_URL/api/items/1
   
   # Get all items
   curl $API_URL/api/items
   ```

2. **Monitor your application:**
   - Check platform dashboard for logs
   - Monitor response times
   - Check error rates

3. **Send the deployment link:**
   - Email to: **dsvjavalinux@gmail.com**
   - Include:
     - API URL
     - Health check endpoint
     - Sample API calls
     - Any credentials (if applicable)

---

## 🐛 Troubleshooting

### Issue: Application won't start

**Solution:** Check logs on your platform dashboard

```bash
# Railway
railway logs

# Heroku
heroku logs --tail

# Render
Check "Logs" tab in dashboard
```

### Issue: Port binding errors

**Solution:** Ensure your application reads PORT from environment:

```properties
server.port=${PORT:8080}
```

### Issue: Build fails

**Solution:** Verify Maven build works locally:

```bash
mvn clean package
java -jar target/item-management-0.0.1-SNAPSHOT.jar
```

### Issue: 404 errors

**Solution:** Make sure you're using the full path:
- ✅ `https://your-app.com/api/items/health`
- ❌ `https://your-app.com/health`

---

## 💡 Best Practices

1. **Use Environment Variables** for sensitive data
2. **Enable HTTPS** (most platforms do this automatically)
3. **Monitor logs** regularly
4. **Set up health checks** (already included at `/api/items/health`)
5. **Document your API** endpoint in the deployment email

---

## 📧 Deployment Email Template

Once deployed, send this email to **dsvjavalinux@gmail.com**:

```
Subject: Item Management API - Deployment Link

Hi,

I've successfully deployed the Item Management REST API. Here are the details:

🔗 API Base URL: https://your-app.railway.app

📍 Endpoints:
- Health Check: GET https://your-app.railway.app/api/items/health
- Get All Items: GET https://your-app.railway.app/api/items
- Get Item by ID: GET https://your-app.railway.app/api/items/{id}
- Create Item: POST https://your-app.railway.app/api/items

📝 Sample Request:
curl -X POST https://your-app.railway.app/api/items \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Sample Item",
    "description": "This is a test item",
    "category": "Electronics",
    "price": 299.99
  }'

✅ Features Implemented:
- Item Model with id, name, description properties
- In-memory ArrayList storage
- RESTful API endpoints (POST, GET)
- Input validation
- Comprehensive documentation

Platform: Railway/Render/Heroku
Technology: Java 21, Spring Boot 4.0.2, Maven

Best regards
```

---

## 🎯 Quick Start Command Summary

### For Railway (Recommended):
```bash
git init
git add .
git commit -m "Item Management API"
git push origin main
# Then use Railway web interface
```

### For Render:
```bash
# Add render.yaml (shown above)
git add .
git commit -m "Add Render config"
git push origin main
# Then use Render web interface
```

### For Heroku:
```bash
heroku create
heroku buildpacks:set heroku/java
git push heroku main
```

---

## ✅ Deployment Checklist

- [ ] Code pushed to GitHub
- [ ] Platform account created
- [ ] Application deployed
- [ ] Health check endpoint working
- [ ] Create item endpoint tested
- [ ] Get item endpoint tested
- [ ] Deployment URL documented
- [ ] Email sent to dsvjavalinux@gmail.com

---

**Need Help?** Check platform-specific documentation:
- [Railway Docs](https://docs.railway.app)
- [Render Docs](https://render.com/docs)
- [Heroku Docs](https://devcenter.heroku.com)

